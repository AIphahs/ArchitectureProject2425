package efrei.propertyStake.controllers;

import efrei.propertyStake.services.NotificationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {
    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping("/send")
    public void sendNotification(@RequestParam String recipient, @RequestParam String message) {
        notificationService.sendEmail(recipient, message);
    }

    @PostMapping("/notify-contributors/{propertyId}")
    public void notifyContributors(@PathVariable String propertyId) {
        notificationService.notifyAllContributors(propertyId);
    }
}
