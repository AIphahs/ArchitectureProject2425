package efrei.propertyStake.controllers;

import efrei.propertyStake.dto.NotificationCreateRequest;
import efrei.propertyStake.models.Notification;
import efrei.propertyStake.models.Investor;
import efrei.propertyStake.services.NotificationService;
import efrei.propertyStake.services.InvestorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    private final NotificationService notificationService;
    private final InvestorService investorService;

    public NotificationController(NotificationService notificationService, InvestorService investorService) {
        this.notificationService = notificationService;
        this.investorService = investorService;
    }

    @GetMapping
    public List<Notification> getAllNotifications() {
        return notificationService.getAllNotifications();
    }

    @GetMapping("/{id}")
    public Notification getNotificationById(@PathVariable UUID id) {
        return notificationService.getNotificationById(id);
    }

    @PostMapping
    public Notification createNotification(@RequestBody NotificationCreateRequest request) {
        // Récupérer l'investor à partir de l'ID fourni
        UUID investorId = request.getInvestorId();
        Investor investor = investorService.getInvestorById(investorId);
        if (investor == null) {
            throw new RuntimeException("Investor not found");
        }

        Notification notif = new Notification();
        notif.setInvestor(investor);
        notif.setMessage(request.getMessage());
        notif.setStatus(request.getStatus());
        notif.setCreationDate(request.getCreationDate());

        return notificationService.createNotification(notif);
    }

    @PutMapping("/{id}")
    public Notification updateNotification(@PathVariable UUID id, @RequestBody Notification updated) {
        return notificationService.updateNotification(id, updated);
    }

    @DeleteMapping("/{id}")
    public boolean deleteNotification(@PathVariable UUID id) {
        return notificationService.deleteNotification(id);
    }
}
