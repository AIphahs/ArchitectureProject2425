//package efrei.propertyStake.controllers;
//
//import efrei.propertyStake.models.Property;
//import efrei.propertyStake.service.PropertyService;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/properties")
//class PropertyController {
//    private final PropertyService propertyService;
//
//    public PropertyController(PropertyService propertyService) {
//        this.propertyService = propertyService;
//    }
//
//    @PostMapping
//    public Property addProperty(@RequestBody Property property) {
//        return propertyService.addProperty(property);
//    }
//
//    @GetMapping
//    public List<Property> listProperties(@RequestParam(required = false, defaultValue = "false") boolean fundingOnly) {
//        return propertyService.listProperties(fundingOnly);
//    }
//
//    @GetMapping("/{id}")
//    public Property getProperty(@PathVariable Long id) {
//        return propertyService.getProperty(id);
//    }
//
//    @PutMapping("/{id}")
//    public void updateProperty(@PathVariable Long id, @RequestBody Property property) {
//        propertyService.updateProperty(id, property);
//    }
//
//    @DeleteMapping("/{id}")
//    public void removeProperty(@PathVariable Long id) {
//        propertyService.removeProperty(id);
//    }
//}
