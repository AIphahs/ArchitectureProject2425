package efrei.propertyStake.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "investments")
public class Investment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    // L'investisseur qui investit
    @ManyToOne
    @JoinColumn(name = "investor_id")
    @JsonBackReference(value = "investor-investment")
    private Investor investor;

    // La propriété dans laquelle on investit
    @ManyToOne
    @JoinColumn(name = "property_id")
    private Property property;

    private double amount;
    private LocalDateTime investmentDate;  // Date à laquelle on a investi

    public Investment() {
    }

    public Investment(Investor investor, Property property, double amount, LocalDateTime investmentDate) {
        this.investor = investor;
        this.property = property;
        this.amount = amount;
        this.investmentDate = investmentDate;
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

    public Property getProperty() {
        return property;
    }
    public void setProperty(Property property) {
        this.property = property;
    }

    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getInvestmentDate() {
        return investmentDate;
    }
    public void setInvestmentDate(LocalDateTime investmentDate) {
        this.investmentDate = investmentDate;
    }
}
