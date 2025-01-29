package efrei.propertyStake.services;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

@Service
public class PaymentGatewayService {

    @Value("${stripe.secret.key}")
    private String stripeSecretKey;

    public boolean processPayment(String userId, double amount) {
        System.out.printf("Processing payment of %.2f for user %s.%n", amount, userId);

        try {
             //Créer une transaction Stripe
//            PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
//                    .setAmount((long) (amount * 100)) // Convertir en cents
//                    .setCurrency("eur")
//                    .setPaymentMethod(paymentMethodId)
//                    .setConfirm(true) // Confirmer immédiatement
//                    .build();
//
//            PaymentIntent paymentIntent = PaymentIntent.create(params);
//
//            return paymentIntent.getId(); // Retourne l'ID du paiement
        return true; // Simuler un paiement réussi

        } catch (Exception e) {
            throw new RuntimeException("Payment failed: " + e.getMessage());
        }
    }
}


