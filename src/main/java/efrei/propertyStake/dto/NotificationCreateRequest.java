package efrei.propertyStake.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class NotificationCreateRequest {
    private UUID investorId;
    private String message;
    private String status;
    private LocalDateTime creationDate;

    // Getters and Setters
    public UUID getInvestorId() {
        return investorId;
    }

    public void setInvestorId(UUID investorId) {
        this.investorId = investorId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }
}
