package efrei.propertyStake.repository;

import efrei.propertyStake.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    // Vous pouvez ajouter des méthodes de requêtes custom ici si besoin
    // Par exemple: Optional<User> findByEmail(String email);
}
