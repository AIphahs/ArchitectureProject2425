package efrei.propertyStake.models;

import java.time.LocalDateTime;
import java.util.UUID;

public class PaymentGateway {
    private UUID paymentGateway_id;
    private UUID investor_id;
    private double amount;
    private String status;
    private LocalDateTime creation_date;

    public PaymentGateway() {}

    public PaymentGateway(UUID paymentGateway_id, UUID investor_id, double amount, String status, LocalDateTime creation_date) {
        this.paymentGateway_id = paymentGateway_id;
        this.investor_id = investor_id;
        this.amount = amount;
        this.status = status;
        this.creation_date = creation_date;
    }

    // Getters and Setters
    public UUID getId() {
        return paymentGateway_id;
    }
    public void setId(UUID paymentGateway_id) {
        this.paymentGateway_id = paymentGateway_id;
    }

    public UUID getInvestorId() {
        return investor_id;
    }
    public void setInvestorId(UUID investor_id) {
        this.investor_id = investor_id;
    }

    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreationDate() {
        return creation_date;
    }
    public void setCreationDate(LocalDateTime creation_date) {
        this.creation_date = creation_date;
    }
}
