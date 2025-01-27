package efrei.propertyStake.repository;

import efrei.propertyStake.models.Investment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface InvestmentRepository extends JpaRepository<Investment, UUID> {
    // Ex: List<Investment> findByInvestorId(UUID investorId);
}
