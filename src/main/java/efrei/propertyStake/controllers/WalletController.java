package efrei.propertyStake.controllers;

import efrei.propertyStake.models.Wallet;
import efrei.propertyStake.services.WalletService;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/wallets")
public class WalletController {
    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @PostMapping("/{id}/deposit")
    public Wallet depositFunds(@PathVariable UUID id, @RequestParam double amount) {
        return walletService.depositFunds(id, amount);
    }

    @GetMapping("/{id}")
    public Wallet getWallet(@PathVariable UUID id) {
        return walletService.getWallet(id);
    }
}
