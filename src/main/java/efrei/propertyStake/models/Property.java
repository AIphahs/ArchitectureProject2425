package efrei.propertyStake.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "properties")
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;
    private double price;                 // Prix total de la propriété
    private double fundedAmount;          // Montant déjà investi
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fundingDeadline;    // Date limite de funding
    private boolean fundingOpen;          // Indique si la propriété est encore ouverte au funding

    private double rentalIncomePercentage; // 6% de revenus locatifs, par ex
    private double appreciationRate;       // 2% de valorisation, etc.

    private String type; // "apartment", "building", etc.

    private boolean certificateDelivered = false;

    // Relation ManyToOne vers l'Agent
    @ManyToOne
    @JoinColumn(name = "agent_id")
    @JsonBackReference(value = "agent-property")
    private Agent agent;

    // Relation OneToMany vers Investment
    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "property-investment")
    private List<Investment> investments = new ArrayList<>();

    // Relation OneToMany vers ShareCertificate
    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "property-share")
    private List<ShareCertificate> shareCertificates = new ArrayList<>();

    public Property() {
    }

    public Property(String name, double price, LocalDate fundingDeadline, boolean fundingOpen,
                    double rentalIncomePercentage, double appreciationRate, String type) {
        this.name = name;
        this.price = price;
        this.fundingDeadline = fundingDeadline;
        this.fundingOpen = fundingOpen;
        this.rentalIncomePercentage = rentalIncomePercentage;
        this.appreciationRate = appreciationRate;
        this.type = type;
        this.fundedAmount = 0.0;
    }

    // Getters / Setters
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
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

    public boolean isFundingOpen() {
        return fundingOpen;
    }
    public void setFundingOpen(boolean fundingOpen) {
        this.fundingOpen = fundingOpen;
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

    public Agent getAgent() {
        return agent;
    }
    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public List<Investment> getInvestments() {
        return investments;
    }
    public void setInvestments(List<Investment> investments) {
        this.investments = investments;
    }

    public List<ShareCertificate> getShareCertificates() {return shareCertificates;}
    public void setShareCertificates(List<ShareCertificate> shareCertificates) {this.shareCertificates = shareCertificates;}

    public void setCertificateDelivered(boolean certificateDelivered) {
        this.certificateDelivered = certificateDelivered;
    }

    public boolean isCertificateDelivered() {
        return certificateDelivered;
    }
}