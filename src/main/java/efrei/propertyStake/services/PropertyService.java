package efrei.propertyStake.services;

import efrei.propertyStake.models.Investment;
import efrei.propertyStake.models.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PropertyService {

    private final Map<UUID, Property> properties = new HashMap<>();
    private final Map<UUID, Investment> investments = new HashMap<>();

    public Property addProperty(Property property) {
        property.setId(UUID.randomUUID());
        property.setIsFundingOpen(true);
        property.setFundingDeadline(LocalDate.now().plusMonths(2));
        properties.put(property.getId(), property);
        return property;
    }

    public List<Property> listProperties(boolean fundingOnly) {
        if (fundingOnly) {
            return properties.values().stream().filter(Property::getIsFundingOpen).collect(Collectors.toList());
        }
        return new ArrayList<>(properties.values());
    }

    public Property getProperty(UUID id) {
        return properties.get(id);
    }

    public void updateProperty(UUID id, Property updatedProperty) {
        Property existingProperty = properties.get(id);
        if (existingProperty != null) {
            updatedProperty.setId(existingProperty.getId());
            properties.put(id, updatedProperty);
        }
    }

    public void removeProperty(UUID id) {
        properties.remove(id);
    }

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private ShareCertificateService shareCertificateService;

    public void completeFunding(UUID propertyId) {
        Property property = properties.get(propertyId);
        if (property.getFundedAmount() >= property.getPrice()) {
            property.setIsFundingOpen(false);
            System.out.println("Funding completed for property: " + property.getName());

            // Notify all contributors
            // notificationService.notifyAllContributors(propertyId.toString());

            // Generate share certificates for all contributors
            investments.values().stream()
                    .filter(investment -> investment.getPropertyId().equals(propertyId))
                    .forEach(investment -> shareCertificateService.generateCertificate(
                            investment.getPropertyId(),
                            investment.getInvestorId(),
                            investment.getPercentageOwned(property.getPrice())
                    ));
        }
    }

}