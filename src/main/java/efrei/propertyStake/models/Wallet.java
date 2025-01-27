package efrei.propertyStake.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "wallets")
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private double balance;

    // Relation OneToOne : le wallet appartient à un seul Investor
    @OneToOne
    @JoinColumn(name = "investor_id")
    @JsonBackReference
    private Investor investor;

    public Wallet() {
    }

    public Wallet(double balance) {
        this.balance = balance;
    }

    // Getters / Setters
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }

    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Investor getInvestor() {
        return investor;
    }
    public void setInvestor(Investor investor) {
        this.investor = investor;
    }
}
