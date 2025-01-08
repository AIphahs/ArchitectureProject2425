package efrei.propertyStake.models;

import java.util.UUID;

public class Wallet {
    private UUID wallet_id;
    private double balance;

    public Wallet() {}

    public Wallet(UUID wallet_id, double balance) {
        this.wallet_id = wallet_id;
        this.balance = balance;
    }

    // Getters and Setters
    public UUID getId() {
        return wallet_id;
    }
    public void setId(UUID wallet_id) {
        this.wallet_id = wallet_id;
    }

    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }
}
