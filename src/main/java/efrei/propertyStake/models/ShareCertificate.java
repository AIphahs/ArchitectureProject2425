package efrei.propertyStake.models;

import java.util.UUID;

public class ShareCertificate {
    private final UUID shareCert_id;
    private final UUID property_id;
    private final UUID investor_id;
    private final double percentage_owned;

    public ShareCertificate(UUID shareCert_id, UUID property_id, UUID investor_id, double percentage_owned) {
        this.shareCert_id = shareCert_id;
        this.property_id = property_id;
        this.investor_id = investor_id;
        this.percentage_owned = percentage_owned;
    }

    public UUID getId() {
        return shareCert_id;
    }

    public UUID getPropertyId() {
        return property_id;
    }

    public UUID getInvestorId() {
        return investor_id;
    }

    public double getQuantity() {
        return percentage_owned;
    }
}
