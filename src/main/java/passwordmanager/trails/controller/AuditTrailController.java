package passwordmanager.trails.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import passwordmanager.trails.entity.AuditTrails;
import passwordmanager.trails.service.AuditTrailService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/audit-trails")
public class AuditTrailController {

    @Autowired
    private AuditTrailService auditTrailService;

    @PostMapping
    public ResponseEntity<?> createAuditTrail(@RequestBody AuditTrails auditTrail) {
        try {
            AuditTrails createdAuditTrail = auditTrailService.createAuditTrail(auditTrail);
            return ResponseEntity.ok(createdAuditTrail);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error creating audit trail: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllAuditTrails() {
        try {
            List<AuditTrails> auditTrails = auditTrailService.getAllAuditTrails();
            return ResponseEntity.ok(auditTrails);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error fetching audit trails: " + e.getMessage());
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getAuditTrailsByUserId(@PathVariable UUID userId) {
        try {
            List<AuditTrails> auditTrails = auditTrailService.getAuditTrailsByUserId(userId);
            return ResponseEntity.ok(auditTrails);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error fetching audit trails for user: " + e.getMessage());
        }
    }
}
