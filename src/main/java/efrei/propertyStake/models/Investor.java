package efrei.propertyStake.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "investors")
public class Investor extends User {
    // Relation OneToOne vers le Wallet
    @OneToOne(mappedBy = "investor", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "investor-wallet")
    private Wallet wallet;

    // Liste d'investments (un investor peut avoir plusieurs investissements)
    @OneToMany(mappedBy = "investor", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "investor-investment")
    private List<Investment> investments = new ArrayList<>();

    // Liste de ShareCertificates (un investor peut avoir plusieurs certificats de parts)
    @OneToMany(mappedBy = "investor", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "investor-share")
    private List<ShareCertificate> shareCertificates = new ArrayList<>();

    // Liste de notifications
    @OneToMany(mappedBy = "investor", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "investor-notification")
    private List<Notification> notifications = new ArrayList<>();

    public Investor() {
        super();
        super.setUserType("INVESTOR");
    }

    public Investor(String firstname, String lastname, String email, String password) {
        super(firstname, lastname, email, password, "INVESTOR");
    }

    // Getters / Setters
    public Wallet getWallet() {
        return wallet;
    }
    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
        if (wallet != null) {
            wallet.setInvestor(this);
        }
    }

    public List<Investment> getInvestments() {
        return investments;
    }
    public void setInvestments(List<Investment> investments) {
        this.investments = investments;
    }

    public List<ShareCertificate> getShareCertificates() {return shareCertificates;}
    public void setShareCertificates(List<ShareCertificate> shareCertificates) {this.shareCertificates = shareCertificates;}

    public List<Notification> getNotifications() {
        return notifications;
    }
    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }
}
