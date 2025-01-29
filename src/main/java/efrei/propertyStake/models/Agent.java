package efrei.propertyStake.models;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Entity
@Table(name = "agents")
public class Agent extends User {

    private String agencyName;


    @OneToMany(mappedBy = "agent", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "agent-property")
    private List<Property> properties = new ArrayList<>();
    public Agent() {
        super();
        super.setUserType("AGENT");
    }

    public Agent(String firstname, String lastname, String email, String password, String agencyName) {
        super(firstname, lastname, email, password, "AGENT");
        this.agencyName = agencyName;
    }

    // Getters / Setters
    public String getAgencyName() {
        return agencyName;
    }
    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    public List<Property> getProperties() {
        return properties;
    }
    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }
}
