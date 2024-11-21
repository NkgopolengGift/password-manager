package passwordmanager.trails.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import passwordmanager.users.entity.User;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "Audit_Trails")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuditTrails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID auditTrailID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Action action;

    @Column(updatable = false)
    private String ipAddress;

    private String deviceInformation;

    @CreationTimestamp
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdDate;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime lastUpdatedDate;
}
