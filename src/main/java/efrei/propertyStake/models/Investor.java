package efrei.propertyStake.models;

import java.util.UUID;

public class Investor extends User {
    private UUID investor_id;
    private UUID user_id;
    private UUID wallet_id;

    public Investor() {}

    public Investor(UUID user_id, String firstname, String lastname, String email, String password, String user_type, UUID wallet_id) {
        super(user_id, firstname, lastname, email, password, user_type);
        this.wallet_id = wallet_id;
    }

    // Getters and Setters
    @Override
    public UUID getId() {
        return investor_id;
    }
    @Override
    public void setId(UUID investor_id) {
        this.investor_id = investor_id;
    }

    public UUID getUserId() {
        return user_id;
    }
    public void setUserId(UUID user_id) {
        this.user_id = user_id;
    }

    public UUID getWalletId() {
        return wallet_id;
    }
    public void setWalletId(UUID wallet_id) {
        this.wallet_id = wallet_id;
    }
}
