package efrei.propertyStake.controllers;

import efrei.propertyStake.models.Investor;
import efrei.propertyStake.services.InvestorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/investors")
public class InvestorController {

    private final InvestorService investorService;

    public InvestorController(InvestorService investorService) {
        this.investorService = investorService;
    }

    @PostMapping
    public Investor createInvestor(@RequestBody Investor investor) {
        return investorService.createInvestor(investor);
    }

    @GetMapping
    public List<Investor> getAllInvestors() {
        return investorService.getAllInvestors();
    }

    @GetMapping("/{id}")
    public Investor getInvestorById(@PathVariable UUID id) {
        return investorService.getInvestorById(id);
    }

    @PutMapping("/{id}")
    public Investor updateInvestor(@PathVariable UUID id, @RequestBody Investor updatedInvestor) {
        return investorService.updateInvestor(id, updatedInvestor);
    }

    @DeleteMapping("/{id}")
    public boolean deleteInvestor(@PathVariable UUID id) {
        return investorService.deleteInvestor(id);
    }
}
