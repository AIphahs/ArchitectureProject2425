package efrei.propertyStake.services;

import efrei.propertyStake.models.Investor;
import efrei.propertyStake.models.Wallet;
import efrei.propertyStake.repository.InvestorRepository;
import efrei.propertyStake.repository.WalletRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class InvestorService {

    private final InvestorRepository investorRepository;
    private final WalletRepository walletRepository;

    public InvestorService(InvestorRepository investorRepository, WalletRepository walletRepository) {
        this.investorRepository = investorRepository;
        this.walletRepository = walletRepository;
    }

    public Investor createInvestor(Investor investor) {
        // Optionnel : créer un wallet associé ?
        Wallet wallet = new Wallet(0.0);
        investor.setWallet(wallet);
        wallet.setInvestor(investor);

        // On sauve l'investor (cascade = ALL doit aussi faire persister le wallet)
        return investorRepository.save(investor);
    }

    public List<Investor> getAllInvestors() {
        return investorRepository.findAll();
    }

    public Investor getInvestorById(UUID id) {
        return investorRepository.findById(id).orElse(null);
    }

    public Investor updateInvestor(UUID id, Investor updatedInvestor) {
        Optional<Investor> optInv = investorRepository.findById(id);
        if (optInv.isPresent()) {
            Investor existing = optInv.get();
            existing.setFirstname(updatedInvestor.getFirstname());
            existing.setLastname(updatedInvestor.getLastname());
            existing.setEmail(updatedInvestor.getEmail());
            existing.setPassword(updatedInvestor.getPassword());

            return investorRepository.save(existing);
        }
        return null;
    }

    public boolean deleteInvestor(UUID id) {
        if (investorRepository.existsById(id)) {
            investorRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
