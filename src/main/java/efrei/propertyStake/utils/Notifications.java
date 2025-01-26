package efrei.propertyStake.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Notifications {

    @Value("${notification.sender}")
    private String sender;

    public void sendNotification(String recipient, String message) {
        System.out.printf("Sending email from %s to %s: %s%n", sender, recipient, message);
    }
}
