package efrei.propertyStake.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PaymentGateway {

    @Value("${payment.gateway.apiKey}")
    private String apiKey;

    public boolean processPayment(String userId, double amount) {
        System.out.printf("Processing payment of %.2f for user %s using API key %s%n", amount, userId, apiKey);
        return true; // Simulated success
    }
}
