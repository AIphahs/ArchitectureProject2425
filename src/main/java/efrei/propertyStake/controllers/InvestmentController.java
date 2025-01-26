package efrei.propertyStake.controllers;

import efrei.propertyStake.models.Investment;
import efrei.propertyStake.services.InvestmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/investments")
public class InvestmentController {
    private final InvestmentService investmentService;

    public InvestmentController(InvestmentService investmentService) {
        this.investmentService = investmentService;
    }

    @PostMapping
    public Investment addInvestment(@RequestBody Investment investment) {
        return investmentService.addInvestment(investment);
    }

    @GetMapping("/user/{userId}")
    public List<Investment> listInvestmentsByUser(@PathVariable UUID userId) {
        return investmentService.listInvestmentsByUser(userId);
    }

    @GetMapping("/{id}")
    public Investment getInvestment(@PathVariable UUID id) {
        return investmentService.getInvestment(id);
    }

    @GetMapping("/property/{propertyId}")
    public List<Investment> getInvestmentsByProperty(@PathVariable UUID propertyId) {
        return investmentService.getInvestmentsByProperty(propertyId);
    }
}
