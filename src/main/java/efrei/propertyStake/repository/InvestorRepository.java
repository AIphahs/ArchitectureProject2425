package efrei.propertyStake.repository;

import efrei.propertyStake.models.Investor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface InvestorRepository extends JpaRepository<Investor, UUID> {
    // Méthodes custom si besoin
}
