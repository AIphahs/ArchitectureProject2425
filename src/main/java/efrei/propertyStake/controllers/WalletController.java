package efrei.propertyStake.controllers;

import efrei.propertyStake.models.Wallet;
import efrei.propertyStake.services.WalletService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/wallets")
public class WalletController {

    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @PostMapping
    public Wallet createWallet(@RequestBody Wallet wallet) {
        return walletService.createWallet(wallet);
    }

    @GetMapping
    public List<Wallet> getAllWallets() {
        return walletService.getAllWallets();
    }

    @GetMapping("/{id}")
    public Wallet getWalletById(@PathVariable UUID id) {
        return walletService.getWalletById(id);
    }

    @PutMapping("/{id}/balance")
    public Wallet updateWalletBalance(@PathVariable UUID id, @RequestParam double balance) {
        return walletService.updateBalance(id, balance);
    }

    @DeleteMapping("/{id}")
    public boolean deleteWallet(@PathVariable UUID id) {
        return walletService.deleteWallet(id);
    }
}
