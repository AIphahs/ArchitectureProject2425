//package efrei.propertyStake.service;
//
//import efrei.propertyStake.models.Property;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.UUID;
//
//@Service
//public class PropertyService {
//    private final Map<UUID, Property> properties = new HashMap<>();
//    private long currentPropertyId = 1;
//
//    public Property addProperty(Property property) {
//        property.setId(currentPropertyId++);
//        properties.put(property.getId(), property);
//        return property;
//    }
//
//    public List<Property> listProperties(boolean fundingOnly) {
//        if (fundingOnly) {
//            return new ArrayList<>(properties.values().stream().filter(Property::isFundingOpen).toList());
//        }
//        return new ArrayList<>(properties.values());
//    }
//
//    public Property getProperty(UUID id) {
//        return properties.get(id);
//    }
//
//    public void updateProperty(UUID id, Property updatedProperty) {
//        Property property = properties.get(id);
//        if (property != null) {
//            updatedProperty.setId(property.getId());
//            properties.put(id, updatedProperty);
//        }
//    }
//
//    public void removeProperty(UUID id) {
//        properties.remove(id);
//    }
//
//    @Scheduled(fixedRate = 60000) // Check funding deadlines every minute
//    public void checkFundingDeadlines() {
//        for (Property property : properties.values()) {
//            if (property.isFundingOpen() && LocalDate.now().isAfter(property.getFundingDeadline())) {
//                property.setFundingOpen(false);
//                refundInvestors(property.getId());
//            }
//        }
//    }
//
//    private void refundInvestors(UUID propertyId) {
//        // TODO: Implement refund logic
//    }
//}
