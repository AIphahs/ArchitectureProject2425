package efrei.propertyStake.controllers;

import efrei.propertyStake.models.Property;
import efrei.propertyStake.services.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/properties")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    @PostMapping
    public Property addProperty(@RequestBody Property property) {
        return propertyService.addProperty(property);
    }

    @GetMapping
    public List<Property> listProperties(@RequestParam(required = false, defaultValue = "false") boolean fundingOnly) {
        return propertyService.listProperties(fundingOnly);
    }

    @GetMapping("/{id}")
    public Property getProperty(@PathVariable UUID id) {
        return propertyService.getProperty(id);
    }

    @PutMapping("/{id}")
    public void updateProperty(@PathVariable UUID id, @RequestBody Property property) {
        propertyService.updateProperty(id, property);
    }

    @DeleteMapping("/{id}")
    public void removeProperty(@PathVariable UUID id) {
        propertyService.removeProperty(id);
    }
}
