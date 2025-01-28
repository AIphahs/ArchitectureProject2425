package efrei.propertyStake.repository;

import efrei.propertyStake.models.Investment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface InvestmentRepository extends JpaRepository<Investment, UUID> {
    List<Investment> findByPropertyId(UUID propertyId);
    List<Investment> findByInvestorId(UUID investorId);
}
