package efrei.propertyStake.services;

import efrei.propertyStake.models.Investment;
import efrei.propertyStake.models.Investor;
import efrei.propertyStake.models.Property;
import efrei.propertyStake.repository.InvestmentRepository;
import efrei.propertyStake.repository.InvestorRepository;
import efrei.propertyStake.repository.PropertyRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class InvestmentService {

    private final InvestmentRepository investmentRepository;
    private final InvestorRepository investorRepository;
    private final PropertyRepository propertyRepository;

    public InvestmentService(InvestmentRepository investmentRepository,
                             InvestorRepository investorRepository,
                             PropertyRepository propertyRepository) {
        this.investmentRepository = investmentRepository;
        this.investorRepository = investorRepository;
        this.propertyRepository = propertyRepository;
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
        // Récupérer l'investor
        Investor investor = investorRepository.findById(investorId).orElseThrow(
                () -> new RuntimeException("Investor not found"));

        // Récupérer la propriété
        Property property = propertyRepository.findById(propertyId).orElseThrow(
                () -> new RuntimeException("Property not found"));

        // Vérifier si fundingOpen + deadline pas dépassée, etc.
        if (!property.isFundingOpen()) {
            throw new RuntimeException("Property is not open for funding");
        }
        // Vérifier si on ne dépasse pas price
        double totalAfterInvestment = property.getFundedAmount() + amount;
        if (totalAfterInvestment > property.getPrice()) {
            throw new RuntimeException("Investment exceeds property funding limit");
        }

        // Créer l'Investment
        Investment investment = new Investment(investor, property, amount, LocalDateTime.now());

        // Mettre à jour la propriété
        property.setFundedAmount(totalAfterInvestment);
        // Si fundedAmount == price => on peut fermer la propriété
        if (totalAfterInvestment >= property.getPrice()) {
            property.setFundingOpen(false);
        }

        // Sauvegarder
        Investment saved = investmentRepository.save(investment);
        propertyRepository.save(property);

        return saved;
    }

    public void deleteInvestment(UUID id) {
        investmentRepository.deleteById(id);
    }

    public List<Investment> getInvestmentsByProperty(UUID propertyId) {
        return investmentRepository.findByPropertyId(propertyId);
    }

    public List<Investment> getInvestmentsByInvestor(UUID investorId) {
        return investmentRepository.findByInvestorId(investorId);
    }
}
