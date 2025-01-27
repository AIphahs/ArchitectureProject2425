package efrei.propertyStake.models;
import jakarta.persistence.*;

import java.util.UUID;
import java.util.UUID;

@Entity
@Table(name = "users") // Table "users"
@Inheritance(strategy = InheritanceType.JOINED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID user_id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String user_type;

    public User() {}

    public User(String firstname, String lastname, String email, String password, String user_type) {

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

