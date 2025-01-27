package efrei.propertyStake.controllers;

import efrei.propertyStake.models.Investment;
import efrei.propertyStake.services.InvestmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/investments")
public class InvestmentController {

    private final InvestmentService investmentService;

    public InvestmentController(InvestmentService investmentService) {
        this.investmentService = investmentService;
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

    @DeleteMapping("/{id}")
    public void deleteInvestment(@PathVariable UUID id) {
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
}
