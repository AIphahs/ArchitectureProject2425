package efrei.propertyStake.models;

import java.util.UUID;

public class Agent extends User {
    private UUID agent_id;
    private UUID user_id;
    private String agencyName;

    public Agent() {}

    public Agent(UUID user_id, String firstname, String lastname, String email, String password, String user_type, String agencyName) {
        super(user_id, firstname, lastname, email, password, user_type);
        this.agencyName = agencyName;
    }

    // Getters and Setters
    @Override
    public UUID getId() {
        return agent_id;
    }
    @Override
    public void setId(UUID agent_id) {
        this.agent_id = agent_id;
    }

    public UUID getUserId() {
        return user_id;
    }
    public void setUserId(UUID user_id) {
        this.user_id = user_id;
    }

    public String getAgencyName() {
        return agencyName;
    }
    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }
}
