package efrei.propertyStake.models;

import java.util.UUID;

public class Portfolio {
    private UUID portfolio_id;
    private UUID investor_id;
    private UUID property_id;
    private double percentage_owned;

    public Portfolio() {}

    public Portfolio(UUID portfolio_id, UUID investor_id, UUID property_id, double percentage_owned) {
        this.portfolio_id = portfolio_id;
        this.investor_id = investor_id;
        this.property_id = property_id;
        this.percentage_owned = percentage_owned;
    }

    // Getters and Setters
    public UUID getId() {
        return portfolio_id;
    }
    public void setId(UUID portfolio_id) {
        this.portfolio_id = portfolio_id;
    }

    public UUID getInvestorId() {
        return investor_id;
    }
    public void setInvestorId(UUID investor_id) {
        this.investor_id = investor_id;
    }

    public UUID getPropertyId() {
        return property_id;
    }
    public void setPropertyId(UUID property_id) {
        this.property_id = property_id;
    }

    public double getPercentageOwned() {
        return percentage_owned;
    }
    public void setPercentageOwned(double percentage_owned) {
        this.percentage_owned = percentage_owned;
    }
}
