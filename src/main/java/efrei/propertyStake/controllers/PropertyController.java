package efrei.propertyStake.controllers;

import efrei.propertyStake.models.Property;
import efrei.propertyStake.services.PropertyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/properties")
public class PropertyController {

    private final PropertyService propertyService;

    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @PostMapping
    public Property createProperty(@RequestBody Property property) {
        return propertyService.createProperty(property);
    }

    @GetMapping
    public List<Property> getAllProperties() {
        return propertyService.getAllProperties();
    }

    @GetMapping("/{id}")
    public Property getPropertyById(@PathVariable UUID id) {
        return propertyService.getPropertyById(id);
    }

    @PutMapping("/{id}")
    public Property updateProperty(@PathVariable UUID id, @RequestBody Property updated) {
        return propertyService.updateProperty(id, updated);
    }

    @DeleteMapping("/{id}")
    public boolean deleteProperty(@PathVariable UUID id) {
        return propertyService.deleteProperty(id);
    }

    @GetMapping("/open")
    public List<Property> getOpenProperties() {
        return propertyService.getOpenPropertiesLimited();
    }

    @PostMapping("/{id}/deliverCertificate")
    public Property deliverCertificate(@PathVariable UUID id) {
        return propertyService.deliverCertificate(id);
    }
}
