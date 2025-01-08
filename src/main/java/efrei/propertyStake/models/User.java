package efrei.propertyStake.models;

import java.util.UUID;

public class User {
    private UUID user_id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String user_type;

    public User() {}

    public User(UUID user_id, String firstname, String lastname, String email, String password, String user_type) {
        this.user_id = user_id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.user_type = user_type;
    }

    // Getters and Setters
    public UUID getId() {
        return user_id;
    }
    public void setId(UUID user_id) {
        this.user_id = user_id;
    }

    public String getFirstname() {
        return firstname;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return user_type;
    }
    public void setUserType(String user_type) {
        this.user_type = user_type;
    }
}
