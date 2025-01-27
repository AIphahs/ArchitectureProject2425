package efrei.propertyStake.repository;

import efrei.propertyStake.models.Agent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AgentRepository extends JpaRepository<Agent, UUID> {
    // Méthodes custom si besoin
}
