package efrei.propertyStake.services;

import efrei.propertyStake.models.Investment;
import efrei.propertyStake.models.Property;
import efrei.propertyStake.models.Wallet;
import efrei.propertyStake.repository.PropertyRepository;
import efrei.propertyStake.repository.WalletRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class FundingDeadlineService {

    private final PropertyRepository propertyRepository;
    private final WalletRepository walletRepository;

    public FundingDeadlineService(PropertyRepository propertyRepository,
                                  WalletRepository walletRepository) {
        this.propertyRepository = propertyRepository;
        this.walletRepository = walletRepository;
    }

    /**
     * Vérifie toutes les propriétés en cours de financement et si leur 'fundingDeadline'
     * est dépassée (et que fundedAmount < price), on rembourse tous les investisseurs
     * et on ferme la propriété.
     *
     * @return la liste des propriétés qui ont été fermées et des montants remboursés.
     */
    public List<String> checkAndRefundIfDeadlinePassed() {
        List<String> results = new ArrayList<>();

        List<Property> allProperties = propertyRepository.findAll();

        for (Property property : allProperties) {
            if (property.isFundingOpen() &&
                    LocalDate.now().isAfter(property.getFundingDeadline()) &&
                    property.getFundedAmount() < property.getPrice()) {

                // => Le délai est dépassé ET pas totalement financé
                // => Remboursement de tous les investors
                double totalRefund = 0.0;
                for (Investment inv : property.getInvestments()) {
                    double invested = inv.getAmount();
                    if (invested > 0) {
                        Wallet w = inv.getInvestor().getWallet();
                        if (w != null) {
                            // Rembourse
                            w.setBalance(w.getBalance() + invested);
                            walletRepository.save(w);
                        }
                        totalRefund += invested;
                        // Pour "annuler" l'investment, soit on le supprime, soit on le met à 0
                        inv.setAmount(0);
                    }
                }

                // On ferme la propriété
                property.setFundingOpen(false);
                // Optionnel: on pourrait remettre fundedAmount = 0, ou le laisser
                property.setFundedAmount(0);
                propertyRepository.save(property);

                // Ajout d'un message dans la liste de résultats
                results.add("Property " + property.getId() +
                        " deadline passed. Refunded total: " + totalRefund);
            }
        }

        return results;
    }
}
