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

    public String getFirstname() {
        return super.getFirstname();
    }
    public void setFirstname(String firstname) {
        super.setFirstname(firstname);
    }

    public String getLastname() {
        return super.getLastname();
    }
    public void setLastname(String lastname) {
        super.setLastname(lastname);
    }

    public String getEmail() {
        return super.getEmail();
    }
    public void setEmail(String email) {
        super.setEmail(email);
    }

    public String getPassword() {
        return super.getPassword();
    }
    public void setPassword(String password) {
        super.setPassword(password);
    }

    public String getUserType() {
        return super.getUserType();
    }
    public void setUserType(String user_type) {
        super.setUserType(user_type);
    }

    public String getAgencyName() {
        return agencyName;
    }
    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }
}
