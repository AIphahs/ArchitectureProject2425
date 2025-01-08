package efrei.propertyStake.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

public class Transaction {
    private UUID transaction_id;
    private UUID wallet_id;
    private double amount;
    private String type;
    private LocalDateTime creation_date;

    public Transaction() {}

    public Transaction(UUID transaction_id, UUID wallet_id, double amount, String type, LocalDateTime creation_date) {
        this.transaction_id = transaction_id;
        this.wallet_id = wallet_id;
        this.amount = amount;
        this.type = type;
        this.creation_date = creation_date;
    }

    // Getters and Setters
    public UUID getId() {
        return transaction_id;
    }
    public void setId(UUID transaction_id) {
        this.transaction_id = transaction_id;
    }

    public UUID getWalletId() {
        return wallet_id;
    }
    public void setWalletId(UUID wallet_id) {
        this.wallet_id = wallet_id;
    }

    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public LocalDateTime getCreationDate() {
        return creation_date;
    }
    public void setCreationDate(LocalDateTime creation_date) {
        this.creation_date = creation_date;
    }
}
