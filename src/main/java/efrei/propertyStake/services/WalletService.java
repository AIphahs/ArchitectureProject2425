package efrei.propertyStake.services;

import efrei.propertyStake.models.Wallet;
import efrei.propertyStake.repository.WalletRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class WalletService {

    private final WalletRepository walletRepository;

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
}
