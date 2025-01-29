package efrei.propertyStake.services;

import efrei.propertyStake.models.Investment;
import efrei.propertyStake.models.Property;
import efrei.propertyStake.models.ShareCertificate;
import efrei.propertyStake.repository.PropertyRepository;
import efrei.propertyStake.repository.InvestmentRepository;
import efrei.propertyStake.repository.ShareCertificateRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PropertyService {

    private final PropertyRepository propertyRepository;
    private final InvestmentRepository investmentRepository;
    private final ShareCertificateRepository shareCertificateRepository;

    public PropertyService(PropertyRepository propertyRepository, InvestmentRepository investmentRepository, ShareCertificateRepository shareCertificateRepository) {
        this.propertyRepository = propertyRepository;
        this.investmentRepository = investmentRepository;
        this.shareCertificateRepository = shareCertificateRepository;
    }

    public Property createProperty(Property property) {
        return propertyRepository.save(property);
    }

    public List<Property> getAllProperties() {
        return propertyRepository.findAll();
    }

    public Property getPropertyById(UUID id) {
        return propertyRepository.findById(id).orElse(null);
    }

    public Property updateProperty(UUID id, Property updated) {
        Optional<Property> optProp = propertyRepository.findById(id);
        if (optProp.isPresent()) {
            Property existing = optProp.get();
            existing.setName(updated.getName());
            existing.setPrice(updated.getPrice());
            existing.setFundingDeadline(updated.getFundingDeadline());
            existing.setFundingOpen(updated.isFundingOpen());
            existing.setRentalIncomePercentage(updated.getRentalIncomePercentage());
            existing.setAppreciationRate(updated.getAppreciationRate());
            existing.setType(updated.getType());
            return propertyRepository.save(existing);
        }
        return null;
    }

    public boolean deleteProperty(UUID id) {
        if (propertyRepository.existsById(id)) {
            propertyRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Property> getOpenPropertiesLimited() {
        return propertyRepository.findTop6ByFundingOpenTrueOrderByFundingDeadlineAsc();
    }

    public Property deliverCertificate(UUID propertyId) {
        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new RuntimeException("Property not found"));

        // Vérifier si fundedAmount >= price
        if (property.getFundedAmount() < property.getPrice()) {
            throw new RuntimeException("Property is not fully funded yet");
        }

        // Récupérer tous les investissements liés à cette propriété
        List<Investment> investments = investmentRepository.findByPropertyId(propertyId);

        if (investments.isEmpty()) {
            throw new RuntimeException("No investments found for this property");
        }

        // Générer un certificat de partage pour chaque investisseur
        for (Investment investment : investments) {
            ShareCertificate certificate = new ShareCertificate(
                    investment.getProperty(),
                    investment.getInvestor(),
                    (investment.getAmount() / property.getPrice()) * 100
            );

            // Sauvegarder le certificat
            shareCertificateRepository.save(certificate);
        }

        // Mettre le champ certificateDelivered à true
        property.setCertificateDelivered(true);
        return propertyRepository.save(property);
    }

}
