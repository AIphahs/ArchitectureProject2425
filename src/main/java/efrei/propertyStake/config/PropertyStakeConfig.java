package efrei.propertyStake.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PropertyStakeConfig {

    /// TODO: Add a @Bean method to provide the notification sender email address
    @Bean
    public String notificationSender() {
        return "noreply@propertystake.com";
    }

    /// TODO: Add a @Bean method to provide the payment gateway API key
    @Bean
    public String paymentGatewayAPIKey() {
        return "your-stripe-api-key-here";
    }
}
