package efrei.propertyStake.models;

import java.util.UUID;

public class Investment {
    private UUID investment_id;
    private UUID property_id;
    private UUID wallet_id;
    private UUID investor_id;
    private double amount;

    public Investment() {}

    public Investment(UUID investment_id, UUID property_id, UUID wallet_id, UUID investor_id, double amount) {
        this.investment_id = investment_id;
        this.property_id = property_id;
        this.wallet_id = wallet_id;
        this.investor_id = investor_id;
        this.amount = amount;
    }

    // Getters and Setters
    public UUID getId() {
        return investment_id;
    }
    public void setId(UUID investment_id) {
        this.investment_id = investment_id;
    }

    public UUID getPropertyId() {
        return property_id;
    }
    public void setPropertyId(UUID property_id) {
        this.property_id = property_id;
    }

    public UUID getWalletId() {
        return wallet_id;
    }
    public void setWalletId(UUID wallet_id) {
        this.wallet_id = wallet_id;
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
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive.");
        }
        this.amount = amount;
    }

    // Method to calculate the percentage of ownership
    public double getPercentageOwned(double totalPropertyPrice) {
        if (totalPropertyPrice <= 0) {
            throw new IllegalArgumentException("Total property price must be positive.");
        }
        return (amount / totalPropertyPrice) * 100;
    }
}
