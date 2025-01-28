package efrei.propertyStake.services;

import efrei.propertyStake.models.Property;
import efrei.propertyStake.repository.PropertyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PropertyService {

    private final PropertyRepository propertyRepository;

    public PropertyService(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
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
        Property prop = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new RuntimeException("Property not found"));

        // Vérifier si fundedAmount >= price
        if (prop.getFundedAmount() < prop.getPrice()) {
            throw new RuntimeException("Property is not fully funded yet");
        }

        // On met le champ certificateDelivered = true
        prop.setCertificateDelivered(true);
        return propertyRepository.save(prop);
    }

}
