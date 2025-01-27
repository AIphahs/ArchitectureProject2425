package efrei.propertyStake.services;

import efrei.propertyStake.models.Notification;
import efrei.propertyStake.repository.NotificationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public Notification createNotification(Notification notif) {
        return notificationRepository.save(notif);
    }

    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    public Notification getNotificationById(UUID id) {
        return notificationRepository.findById(id).orElse(null);
    }

    public Notification updateNotification(UUID id, Notification updated) {
        Notification n = getNotificationById(id);
        if (n != null) {
            n.setMessage(updated.getMessage());
            n.setCreationDate(updated.getCreationDate());
            n.setStatus(updated.getStatus());
            return notificationRepository.save(n);
        }
        return null;
    }

    public boolean deleteNotification(UUID id) {
        if (notificationRepository.existsById(id)) {
            notificationRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
