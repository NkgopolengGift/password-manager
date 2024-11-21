package passwordmanager.trails.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import passwordmanager.trails.entity.AuditTrails;

import java.util.List;
import java.util.UUID;

@Repository
public interface AuditTrailRepository extends JpaRepository<AuditTrails, UUID> {
    List<AuditTrails> findByUser_UserId(UUID userId);
}
