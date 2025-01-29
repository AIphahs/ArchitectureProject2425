package efrei.propertyStake.repository;

import efrei.propertyStake.models.ShareCertificate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ShareCertificateRepository extends JpaRepository<ShareCertificate, UUID> {

}
