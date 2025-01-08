package efrei.propertyStake.models;

import java.time.LocalDate;
import java.util.UUID;

public class Property {
    private UUID property_id;
    private String name;
    private double price;
    private double rentalIncomePercentage;
    private double appreciationRate;
    private String type;
    private boolean isFundingOpen;
    private double fundedAmount;
    private LocalDate fundingDeadline;

    public Property() {}

    public Property(UUID property_id, String name, double price, double rentalIncomePercentage, double appreciationRate, String type, boolean isFundingOpen, double fundedAmount, LocalDate fundingDeadline) {
        this.property_id = property_id;
        this.name = name;
        this.price = price;
        this.rentalIncomePercentage = rentalIncomePercentage;
        this.appreciationRate = appreciationRate;
        this.type = type;
        this.isFundingOpen = isFundingOpen;
        this.fundedAmount = fundedAmount;
        this.fundingDeadline = fundingDeadline;
    }

    // Getters and Setters
    public UUID getId() {
        return property_id;
    }

    public void setId(UUID property_id) {
        this.property_id = property_id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    public double getRentalIncomePercentage() {
        return rentalIncomePercentage;
    }
    public void setRentalIncomePercentage(double rentalIncomePercentage) {
        this.rentalIncomePercentage = rentalIncomePercentage;
    }

    public double getAppreciationRate() {
        return appreciationRate;
    }
    public void setAppreciationRate(double appreciationRate) {
        this.appreciationRate = appreciationRate;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public boolean getIsFundingOpen() {
        return isFundingOpen;
    }
    public void setIsFundingOpen(boolean isFundingOpen) {
        this.isFundingOpen = isFundingOpen;
    }

    public double getFundedAmount() {
        return fundedAmount;
    }
    public void setFundedAmount(double fundedAmount) {
        this.fundedAmount = fundedAmount;
    }

    public LocalDate getFundingDeadline() {
        return fundingDeadline;
    }
    public void setFundingDeadline(LocalDate fundingDeadline) {
        this.fundingDeadline = fundingDeadline;
    }

}