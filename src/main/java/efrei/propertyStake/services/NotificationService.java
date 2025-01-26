package efrei.propertyStake.services;

import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    public void sendEmail(String recipient, String message) {
        System.out.printf("Sending email to %s: %s%n", recipient, message);
    }

    public void notifyAllContributors(String propertyId) {
        // Simulate notification logic for all contributors
        System.out.printf("Notifying all contributors for property %s.%n", propertyId);
    }
}

