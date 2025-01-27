package efrei.propertyStake.repository;

import efrei.propertyStake.models.Property;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PropertyRepository extends JpaRepository<Property, UUID> {
    List<Property> findTop6ByFundingOpenTrueOrderByFundingDeadlineAsc();
}
