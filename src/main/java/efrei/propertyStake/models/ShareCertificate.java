package efrei.propertyStake.models;

import java.util.UUID;

public class ShareCertificate {
    private UUID shareCert_id;
    private UUID property_id;
    private UUID investor_id;
    private double percentage_owned;
    private java.time.LocalDate issueDate;

    public ShareCertificate(UUID shareCert_id, UUID property_id, UUID investor_id, double percentage_owned) {
        this.shareCert_id = shareCert_id;
        this.property_id = property_id;
        this.investor_id = investor_id;
        this.percentage_owned = percentage_owned;
    }

    public ShareCertificate() {}

    // Getters and Setters
    public UUID getShareCert_id() {
        return shareCert_id;
    }
    public void setShareCert_id(UUID shareCert_id) {
        this.shareCert_id = shareCert_id;
    }

    public UUID getProperty_id() {
        return property_id;
    }
    public void setProperty_id(UUID property_id) {
        this.property_id = property_id;
    }

    public UUID getInvestor_id() {
        return investor_id;
    }
    public void setInvestor_id(UUID investor_id) {
        this.investor_id = investor_id;
    }

    public double getPercentage_owned() {
        return percentage_owned;
    }
    public void setPercentage_owned(double percentage_owned) {
        this.percentage_owned = percentage_owned;
    }

    public java.time.LocalDate getIssueDate() {
        return issueDate;
    }
    public void setIssueDate(java.time.LocalDate issueDate) {
        this.issueDate = issueDate;
    }
}
