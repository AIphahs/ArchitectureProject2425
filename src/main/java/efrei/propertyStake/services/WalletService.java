package efrei.propertyStake.services;

import efrei.propertyStake.models.Wallet;
import efrei.propertyStake.repository.WalletRepository;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class WalletService {

    private final WalletRepository walletRepository;
    private final PaymentGatewayService paymentGatewayService;

    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    public Wallet createWallet(Wallet wallet) {
        return walletRepository.save(wallet);
    }

    public List<Wallet> getAllWallets() {
        return walletRepository.findAll();
    }

    public Wallet getWalletById(UUID id) {
        return walletRepository.findById(id).orElse(null);
    }

    public Wallet updateBalance(UUID walletId, double newBalance) {
        Wallet w = getWalletById(walletId);
        if (w != null) {
            w.setBalance(w.getBalance() + newBalance);
            return walletRepository.save(w);
        }
        return null;
    }

    public boolean deleteWallet(UUID id) {
        if (walletRepository.existsById(id)) {
            walletRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Transactional
    public String addFundsToWallet(String investorId, double amount, String paymentMethodId) {
        // Étape 1: Effectuer le paiement via Stripe
        String transactionId = paymentGatewayService.processPayment(paymentMethodId, amount);

        // Étape 2: Trouver le Wallet de l'investisseur
        Wallet wallet = walletRepository.findById(UUID.fromString(investorId))
                .orElseThrow(() -> new RuntimeException("Wallet not found"));

        // Étape 3: Ajouter l'argent au Wallet
        wallet.setBalance(wallet.getBalance() + amount);
        walletRepository.save(wallet);

        return transactionId; // Retourne l'ID de la transaction Stripe
    }
}
