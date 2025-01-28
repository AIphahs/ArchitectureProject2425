package efrei.propertyStake.dto;

import java.util.UUID;

public class RentalDistributionResult {
    private UUID propertyId;
    private UUID investorId;
    private double amountDistributed;

    public RentalDistributionResult(UUID propertyId, UUID investorId, double amountDistributed) {
        this.propertyId = propertyId;
        this.investorId = investorId;
        this.amountDistributed = amountDistributed;
    }

    // Getters / Setters
    public UUID getPropertyId() {
        return propertyId;
    }
    public void setPropertyId(UUID propertyId) {
        this.propertyId = propertyId;
    }
    public UUID getInvestorId() {
        return investorId;
    }
    public void setInvestorId(UUID investorId) {
        this.investorId = investorId;
    }
    public double getAmountDistributed() {
        return amountDistributed;
    }
    public void setAmountDistributed(double amountDistributed) {
        this.amountDistributed = amountDistributed;
    }
}
