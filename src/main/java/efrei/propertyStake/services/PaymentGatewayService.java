package efrei.propertyStake.services;

import org.springframework.stereotype.Service;

@Service
public class PaymentGatewayService {

    public boolean processPayment(String userId, double amount) {
        System.out.printf("Processing payment of %.2f for user %s.%n", amount, userId);
        return true; // Simulate successful payment
    }
}


