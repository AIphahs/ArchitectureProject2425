package efrei.propertyStake.controllers;

import efrei.propertyStake.services.FundingDeadlineService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/funding")
public class FundingDeadlineController {

    private final FundingDeadlineService fundingDeadlineService;

    public FundingDeadlineController(FundingDeadlineService fundingDeadlineService) {
        this.fundingDeadlineService = fundingDeadlineService;
    }

    /**
     * Endpoint pour vérifier toutes les propriétés et rembourser si deadline passée
     * Ex: POST /funding/checkDeadline
     */
    @PostMapping("/checkDeadline")
    public List<String> checkFundingDeadline() {
        return fundingDeadlineService.checkAndRefundIfDeadlinePassed();
    }
}
