package efrei.propertyStake.services;

import efrei.propertyStake.models.Wallet;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class WalletService {
    private final Map<UUID, Wallet> wallets = new HashMap<>();

    public Wallet createWallet(UUID userId) {
        Wallet wallet = new Wallet();
        wallet.setId(UUID.randomUUID());
        wallet.setBalance(0.0);
        wallets.put(userId, wallet);
        return wallet;
    }

    public Wallet depositFunds(UUID walletId, double amount) {
        Wallet wallet = wallets.get(walletId);
        if (wallet != null) {
            wallet.setBalance(wallet.getBalance() + amount);
        }
        return wallet;
    }

    public Wallet getWallet(UUID walletId) {
        return wallets.get(walletId);
    }
}
