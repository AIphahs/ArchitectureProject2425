package efrei.propertyStake.controllers;

import efrei.propertyStake.services.PaymentGatewayService;
import org.springframework.web.bind.annotation.*;
import efrei.propertyStake.services.WalletService;


@RestController
@RequestMapping("/api/payments")
public class PaymentGatewayController {
    private final PaymentGatewayService paymentGatewayService;
    private final WalletService walletService;

    public PaymentGatewayController(PaymentGatewayService paymentGatewayService, WalletService walletService) {
        this.paymentGatewayService = paymentGatewayService;
        this.walletService = walletService;
    }

    @PostMapping("/process")
    public boolean processPayment(@RequestParam String userId, @RequestParam double amount) {
        return paymentGatewayService.processPayment(userId, amount);
    }

    @PostMapping("/addFunds")
    public String addFundsToWallet(@RequestParam String investorId, @RequestParam double amount, @RequestParam String paymentMethodId) {
        return walletService.addFundsToWallet(investorId, amount, paymentMethodId);
    }
}
