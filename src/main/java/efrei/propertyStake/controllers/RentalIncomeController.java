package efrei.propertyStake.controllers;

import efrei.propertyStake.dto.RentalDistributionResult;
import efrei.propertyStake.services.RentalIncomeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rental")
public class RentalIncomeController {

    private final RentalIncomeService rentalIncomeService;

    public RentalIncomeController(RentalIncomeService rentalIncomeService) {
        this.rentalIncomeService = rentalIncomeService;
    }

    /**
     * Endpoint pour déclencher la distribution mensuelle des loyers
     * URL : POST /rental/distribute
     */
    @PostMapping("/distribute")
    public List<RentalDistributionResult> distributeMonthlyRent() {
        return rentalIncomeService.distributeMonthlyRental();
    }
}
