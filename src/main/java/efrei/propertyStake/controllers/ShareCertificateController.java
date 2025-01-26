package efrei.propertyStake.controllers;

import efrei.propertyStake.models.ShareCertificate;
import efrei.propertyStake.services.ShareCertificateService;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/share-certificates")
public class ShareCertificateController {
    private final ShareCertificateService shareCertificateService;

    public ShareCertificateController(ShareCertificateService shareCertificateService) {
        this.shareCertificateService = shareCertificateService;
    }

    @PostMapping("/generate/{investmentId}")
    public ShareCertificate generateCertificate(
            @RequestParam UUID propertyId,
            @RequestParam UUID investorId,
            @RequestParam double percentageOwned) {
    return shareCertificateService.generateCertificate(propertyId, investorId, percentageOwned);
    }
}
