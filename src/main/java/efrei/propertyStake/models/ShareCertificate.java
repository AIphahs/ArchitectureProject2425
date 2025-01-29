package efrei.propertyStake.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "shareCertificate")
public class ShareCertificate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID shareCert_id;

    @ManyToOne
    @JoinColumn(name = "property_id", nullable = false)
    @JsonBackReference(value = "property-share")
    private Property property;

    @ManyToOne
    @JoinColumn(name = "investor_id", nullable = false)
    @JsonBackReference(value = "investor-share")
    private Investor investor;

    private double percentage_owned;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate issueDate;

    public ShareCertificate(Property property, Investor investor, double percentage_owned) {
        this.property = property;
        this.investor = investor;
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

    public Property getProperty() {
        return property;
    }
    public void setProperty(Property property) {
        this.property = property;
    }

    public Investor getInvestor() {
        return investor;
    }
    public void setInvestor(Investor investor_id) {
        this.investor = investor_id;
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
