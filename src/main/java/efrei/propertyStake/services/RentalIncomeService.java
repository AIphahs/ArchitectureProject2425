package efrei.propertyStake.services;

import efrei.propertyStake.dto.RentalDistributionResult;
import efrei.propertyStake.models.Investment;
import efrei.propertyStake.models.Property;
import efrei.propertyStake.models.Wallet;
import efrei.propertyStake.repository.PropertyRepository;
import efrei.propertyStake.repository.WalletRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RentalIncomeService {

    private final PropertyRepository propertyRepository;
    private final WalletRepository walletRepository;

    public RentalIncomeService(PropertyRepository propertyRepository,
                               WalletRepository walletRepository) {
        this.propertyRepository = propertyRepository;
        this.walletRepository = walletRepository;
    }

    /**
     * Distribue le revenu locatif mensuel pour toutes les propriétés,
     * et crédite les wallets des investisseurs correspondants.
     *
     * @return la liste des distributions effectuées
     */
    public List<RentalDistributionResult> distributeMonthlyRental() {
        List<RentalDistributionResult> results = new ArrayList<>();

        // Récupérer toutes les propriétés
        List<Property> properties = propertyRepository.findAll();

        // Pour chaque propriété
        for (Property property : properties) {

            // 1) Vérifier si la propriété a bien le "certificat de propriété" livré
            //    (ou un autre champ signifiant que la propriété est "en phase d'exploitation")
            if (!property.isCertificateDelivered()) {
                // Si pas délivré, on passe à la suivante
                continue;
            }

            // 2) Vérifier si la propriété est entièrement financée (optionnel)
            if (property.getFundedAmount() < property.getPrice()) {
                // Si pas totalement financée, on ne distribue pas de revenu
                continue;
            }

            // 3) Vérifier le taux de location
            double rentPercent = property.getRentalIncomePercentage();
            if (rentPercent <= 0) {
                // Pas de revenus locatifs configurés
                continue;
            }

            // 4) Calculer le revenu mensuel
            // Hypothèse: rentPercent est un taux annuel (ex: 6%), donc on divise par 12
            double monthlyRent = property.getPrice() * (rentPercent / 100.0) / 12.0;

            // 5) Répartir monthlyRent entre les investisseurs
            // => part = (investment.amount / property.price) * monthlyRent
            for (Investment inv : property.getInvestments()) {
                if (inv.getAmount() <= 0) {
                    continue;
                }

                double ratio = inv.getAmount() / property.getPrice();
                double distribution = monthlyRent * ratio;

                // Crédite le wallet de l'investor
                Wallet w = inv.getInvestor().getWallet();
                if (w != null) {
                    double oldBalance = w.getBalance();
                    w.setBalance(oldBalance + distribution);
                    walletRepository.save(w);

                    results.add(new RentalDistributionResult(
                            property.getId(),
                            inv.getInvestor().getId(),
                            distribution
                    ));
                }
            }
        }

        return results;
    }

}
