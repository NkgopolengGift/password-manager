package passwordmanager.trails.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import passwordmanager.trails.entity.AuditTrails;
import passwordmanager.trails.repository.AuditTrailRepository;

import java.util.List;
import java.util.UUID;

@Service
public class AuditTrailService {

    @Autowired
    private AuditTrailRepository auditTrailRepository;

    public AuditTrails createAuditTrail(AuditTrails auditTrail) {
        return auditTrailRepository.save(auditTrail);
    }

    public List<AuditTrails> getAllAuditTrails() {
        return auditTrailRepository.findAll();
    }

    public List<AuditTrails> getAuditTrailsByUserId(UUID userId) {
        List<AuditTrails> trails = auditTrailRepository.findByUser_UserId(userId);
        if (trails.isEmpty()) {
            throw new IllegalArgumentException("No audit trails found for user with ID: " + userId);
        }
        return trails;
    }
}
