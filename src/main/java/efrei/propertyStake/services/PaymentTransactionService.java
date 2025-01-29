package efrei.propertyStake.services;

import efrei.propertyStake.models.PaymentTransaction;
import efrei.propertyStake.models.Investor;
import efrei.propertyStake.models.Wallet;
import efrei.propertyStake.repository.PaymentTransactionRepository;
import efrei.propertyStake.repository.InvestorRepository;
import efrei.propertyStake.repository.WalletRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class PaymentTransactionService {

    private final PaymentTransactionRepository paymentTransactionRepository;
    private final InvestorRepository investorRepository;
    private final WalletRepository walletRepository;

    public PaymentTransactionService(PaymentTransactionRepository paymentTransactionRepository,
                                     InvestorRepository investorRepository,
                                     WalletRepository walletRepository) {
        this.paymentTransactionRepository = paymentTransactionRepository;
        this.investorRepository = investorRepository;
        this.walletRepository = walletRepository;
    }

    public PaymentTransaction createTransaction(UUID investorId, double amount, String status) {
        // Récupérer l'investor
        Investor investor = investorRepository.findById(investorId)
                .orElseThrow(() -> new RuntimeException("Investor not found"));

        PaymentTransaction tx = new PaymentTransaction(investor, amount, status, LocalDateTime.now());
        PaymentTransaction saved = paymentTransactionRepository.save(tx);

        // Optionnel : si status = "SUCCESS", on crédite le wallet
        if ("SUCCESS".equalsIgnoreCase(status) && investor.getWallet() != null) {
            Wallet w = investor.getWallet();
            w.setBalance(w.getBalance() + amount);
            walletRepository.save(w);
        }

        return saved;
    }

    public PaymentTransaction getTransactionById(UUID id) {
        return paymentTransactionRepository.findById(id).orElse(null);
    }

    public List<PaymentTransaction> getAllTransactions() {
        return paymentTransactionRepository.findAll();
    }

    public PaymentTransaction updateStatusTransaction(UUID id, String status) {
        PaymentTransaction transaction = paymentTransactionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));
        transaction.setStatus(status);
        return paymentTransactionRepository.save(transaction);
    }

    public boolean deleteTransaction(UUID id) {
        if (paymentTransactionRepository.existsById(id)) {
            paymentTransactionRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
