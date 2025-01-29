package efrei.propertyStake.controllers;

import efrei.propertyStake.models.Investment;
import efrei.propertyStake.models.Investor;
import efrei.propertyStake.models.Property;
import efrei.propertyStake.models.Wallet;
import efrei.propertyStake.services.InvestmentService;
import efrei.propertyStake.services.PropertyService;
import efrei.propertyStake.services.WalletService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/investments")
public class InvestmentController {

    private final InvestmentService investmentService;
    private final WalletService walletService;
    private final PropertyService propertyService;

    public InvestmentController(InvestmentService investmentService, WalletService walletService, PropertyService propertyService) {
        this.investmentService = investmentService;
        this.walletService = walletService;
        this.propertyService = propertyService;
    }

    @GetMapping
    public List<Investment> getAllInvestments() {
        return investmentService.getAllInvestments();
    }

    @GetMapping("/{id}")
    public Investment getInvestmentById(@PathVariable UUID id) {
        return investmentService.getInvestmentById(id);
    }

    // Ex: { "investorId": "...", "propertyId": "...", "amount": 5000.0 }
    @PostMapping("/invest")
    public Investment invest(@RequestBody InvestRequest request) {
        return investmentService.invest(
                request.getInvestorId(),
                request.getPropertyId(),
                request.getAmount()
        );
    }
    // Ex: { "investorId": "...", "propertyId": "...", "amount": 5000.0 }
    @PostMapping("/fund/{propertyId}")
    public String fundProperty(@PathVariable Long propertyId, @RequestParam double amount) {
        try {
            investmentService.fundProperty(propertyId, amount);
            return "Funding successful";
        } catch (RuntimeException e) {
            return "Funding failed: " + e.getMessage();
        }
    }

    @DeleteMapping("/{id}")
    public void deleteInvestment(@PathVariable UUID id) {

        Investment investment = investmentService.getInvestmentById(id);
        if (investment == null) {
            throw new RuntimeException("Investment not found");
        }

        Investor investor = investment.getInvestor();
        Wallet wallet = investor.getWallet();
        Property property = investment.getProperty();

        double amount = investment.getAmount();
        property.setFundedAmount(property.getFundedAmount() - amount);
        double newWalletBalance = wallet.getBalance() + amount;

        propertyService.updateProperty(property.getId(), property);
        walletService.updateBalance(wallet.getId(), amount);

        investmentService.deleteInvestment(id);
    }

    // DTO interne
    public static class InvestRequest {
        private UUID investorId;
        private UUID propertyId;
        private double amount;

        // getters & setters
        public UUID getInvestorId() {
            return investorId;
        }
        public void setInvestorId(UUID investorId) {
            this.investorId = investorId;
        }
        public UUID getPropertyId() {
            return propertyId;
        }
        public void setPropertyId(UUID propertyId) {
            this.propertyId = propertyId;
        }
        public double getAmount() {
            return amount;
        }
        public void setAmount(double amount) {
            this.amount = amount;
        }
    }

    @GetMapping("/property/{propertyId}")
    public List<Investment> getInvestmentsByProperty(@PathVariable UUID propertyId) {
        return investmentService.getInvestmentsByProperty(propertyId);
    }

    @GetMapping("/investor/{investorId}")
    public List<Investment> getInvestmentsByInvestor(@PathVariable UUID investorId) {
        return investmentService.getInvestmentsByInvestor(investorId);
    }
}
