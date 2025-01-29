package efrei.propertyStake.services;

import efrei.propertyStake.models.Investment;
import efrei.propertyStake.models.Investor;
import efrei.propertyStake.models.Property;
import efrei.propertyStake.models.Wallet;
import efrei.propertyStake.repository.InvestmentRepository;
import efrei.propertyStake.repository.InvestorRepository;
import efrei.propertyStake.repository.PropertyRepository;
import efrei.propertyStake.repository.WalletRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class InvestmentService {

    private final InvestmentRepository investmentRepository;
    private final InvestorRepository investorRepository;
    private final PropertyRepository propertyRepository;
    private final WalletRepository walletRepository;

    public InvestmentService(InvestmentRepository investmentRepository,
                             InvestorRepository investorRepository,
                             PropertyRepository propertyRepository,
                             WalletRepository walletRepository) {
        this.investmentRepository = investmentRepository;
        this.investorRepository = investorRepository;
        this.propertyRepository = propertyRepository;
        this.walletRepository = walletRepository;
    }

    public List<Investment> getAllInvestments() {
        return investmentRepository.findAll();
    }

    public Investment getInvestmentById(UUID id) {
        return investmentRepository.findById(id).orElse(null);
    }

    /**
     * Méthode pour créer un nouvel investissement.
     * Vérifie la propriété, l'investisseur, et met à jour le fundedAmount.
     */
    public Investment invest(UUID investorId, UUID propertyId, double amount) {
        // 1. Récupérer l'investor
        Investor investor = investorRepository.findById(investorId)
                .orElseThrow(() -> new RuntimeException("Investor not found"));

        // 2. Vérifier le wallet de l'investor
        Wallet wallet = investor.getWallet();
        if (wallet == null) {
            throw new RuntimeException("Investor does not have a wallet");
        }

        // 3. Vérifier si le montant investi est >= 500 EUR
        if (amount < 500) {
            throw new RuntimeException("Minimum investment is 500 EUR");
        }

        // 4. Vérifier que le wallet a assez de fonds pour cet investissement
        if (wallet.getBalance() < amount) {
            throw new RuntimeException("Insufficient wallet balance for this investment");
        }

        // 5. Récupérer la propriété
        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new RuntimeException("Property not found"));

        // 6. Vérifier si la propriété est encore ouverte au funding
        if (!property.isFundingOpen()) {
            throw new RuntimeException("Property is not open for funding");
        }

        // 7. Vérifier qu'on ne dépasse pas le prix total
        double totalAfterInvestment = property.getFundedAmount() + amount;
        if (totalAfterInvestment > property.getPrice()) {
            throw new RuntimeException("Investment exceeds property funding limit");
        }


        // 8. Débiter le wallet
        double oldBalance = wallet.getBalance();
        wallet.setBalance(oldBalance - amount);
        walletRepository.save(wallet);

        // 9. Créer l'Investment
        Investment investment = new Investment(investor, property, amount, LocalDateTime.now());

        // 10. Mettre à jour la propriété
        property.setFundedAmount(totalAfterInvestment);

        // Si fundedAmount == price => on peut fermer la propriété
        if (totalAfterInvestment >= property.getPrice()) {
            property.setFundingOpen(false);
        }

        // 11. Sauvegarder l'investment et la propriété
        Investment saved = investmentRepository.save(investment);
        propertyRepository.save(property);

        return saved;
    }


    public void deleteInvestment(UUID id) {
        investmentRepository.deleteById(id);
    }

    public List<Investment> getInvestmentsByProperty(UUID propertyId) {
        Property property = propertyRepository.findById(propertyId).orElseThrow(
                () -> new RuntimeException("Property not found"));

        // Récupérez tous les investissements liés à cette propriété
        return investmentRepository.findByPropertyId(propertyId);
    }

    public List<Investment> getInvestmentsByInvestor(UUID investorId) {
        return investmentRepository.findByInvestorId(investorId);
    }

    // Méthode pour financer une propriété (pour les agents) - transaction synchronisée pour éviter les problèmes de concurrence (plusieurs agents qui financent en même temps)
    @Transactional
    public synchronized boolean fundProperty(Long propertyId, double amount) {
        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new RuntimeException("Property not found"));

        // Vérification de la limite de financement
        if (property.getCurrentFunding() + amount > property.getTotalFundingRequired()) {
            throw new RuntimeException("Funding exceeds the required amount");
        }

        // Mise à jour du financement
        property.setCurrentFunding(property.getCurrentFunding() + amount);

        // Sauvegarde de la propriété
        propertyRepository.save(property);

        return true;
    }
}
