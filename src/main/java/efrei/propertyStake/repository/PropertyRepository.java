package efrei.propertyStake.repository;

import efrei.propertyStake.models.Property;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import jakarta.persistence.LockModeType;
import java.util.Optional;

import java.util.List;
import java.util.UUID;

public interface PropertyRepository extends JpaRepository<Property, UUID> {
    List<Property> findTop6ByFundingOpenTrueOrderByFundingDeadlineAsc();

    // Verrouillage optimiste pour la méthode findById (pour éviter les problèmes de concurrence)
    @Lock(LockModeType.OPTIMISTIC) // Verrouillage optimiste
    Optional<Property> findById(Long id);

    // Verrouillage pessimiste pour la méthode findByIdWithLock (pour éviter les problèmes de concurrence)
    @Lock(LockModeType.PESSIMISTIC_WRITE) // Verrouillage pessimiste
    @Query("SELECT p FROM Property p WHERE p.id = :id")
    Property findByIdWithLock(@Param("id") Long id);
}