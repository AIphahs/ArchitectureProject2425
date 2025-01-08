package efrei.propertyStake.models;

import java.time.LocalDateTime;
import java.util.UUID;

public class Notificaton {
    private UUID notification_id;
    private UUID investor_id;
    private String message;
    private LocalDateTime creation_date;
    private String status;

    public Notificaton() {}

    public Notificaton(UUID notification_id, UUID investor_id, String message, LocalDateTime creation_date, String status) {
        this.notification_id = notification_id;
        this.investor_id = investor_id;
        this.message = message;
        this.creation_date = creation_date;
        this.status = status;
    }

    // Getters and Setters
    public UUID getId() {
        return notification_id;
    }
    public void setId(UUID notification_id) {
        this.notification_id = notification_id;
    }

    public UUID getInvestorId() {
        return investor_id;
    }
    public void setInvestorId(UUID investor_id) {
        this.investor_id = investor_id;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getCreationDate() {
        return creation_date;
    }
    public void setCreationDate(LocalDateTime creation_date) {
        this.creation_date = creation_date;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}
