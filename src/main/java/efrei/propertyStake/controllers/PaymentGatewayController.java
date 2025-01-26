package efrei.propertyStake.controllers;

import efrei.propertyStake.services.PaymentGatewayService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentGatewayController {
    private final PaymentGatewayService paymentGatewayService;

    public PaymentGatewayController(PaymentGatewayService paymentGatewayService) {
        this.paymentGatewayService = paymentGatewayService;
    }

    @PostMapping("/process")
    public boolean processPayment(@RequestParam String userId, @RequestParam double amount) {
        return paymentGatewayService.processPayment(userId, amount);
    }
}
