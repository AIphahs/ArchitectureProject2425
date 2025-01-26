package efrei.propertyStake.services;

import efrei.propertyStake.models.ShareCertificate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ShareCertificateService {

    public ShareCertificate generateCertificate(UUID propertyId, UUID investorId, double percentageOwned) {
        ShareCertificate certificate = new ShareCertificate();
        certificate.setShareCert_id(UUID.randomUUID());
        certificate.setProperty_id(propertyId);
        certificate.setInvestor_id(investorId);
        certificate.setPercentage_owned(percentageOwned);
        certificate.setIssueDate(java.time.LocalDate.now().plusWeeks(2));
        System.out.printf("Certificate generated for property %s and investor %s.%n", propertyId, investorId);
        return certificate;
    }
}
