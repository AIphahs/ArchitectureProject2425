package efrei.propertyStake.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    // On notifie un investisseur (vous pouvez notifier aussi un Agent, si besoin)
    @ManyToOne
    @JoinColumn(name = "investor_id")
    @JsonBackReference
    private Investor investor;

    private String message;
    private LocalDateTime creationDate;
    private String status; // par ex. "SENT", "READ", etc.

    public Notification() {
    }

    public Notification(Investor investor, String message, LocalDateTime creationDate, String status) {
        this.investor = investor;
        this.message = message;
        this.creationDate = creationDate;
        this.status = status;
    }

    // Getters / Setters
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }

    public Investor getInvestor() {
        return investor;
    }
    public void setInvestor(Investor investor) {
        this.investor = investor;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }
    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}
