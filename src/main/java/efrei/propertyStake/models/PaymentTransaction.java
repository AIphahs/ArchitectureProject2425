package efrei.propertyStake.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "payment_transactions")
public class PaymentTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "investor_id")
    private Investor investor;

    private double amount;
    private String status;        // "SUCCESS", "FAILED", etc.
    private LocalDateTime createdAt; // date de la transaction

    public PaymentTransaction() {
    }

    public PaymentTransaction(Investor investor, double amount, String status, LocalDateTime createdAt) {
        this.investor = investor;
        this.amount = amount;
        this.status = status;
        this.createdAt = createdAt;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
