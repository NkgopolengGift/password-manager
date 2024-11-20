package passwordmanager.password.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import passwordmanager.users.entity.User;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "Passwords")
@Data
@NoArgsConstructor
public class Password {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID passwordId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @Column(name = "service_name")
    private String serviceName;

    @Column(name = "service_url")
    private String serviceWebsite;

    @Column(name = "service_email")
    private String serviceEmail;

    @Column(name = "service_username")
    private String serviceUsername;

    @Column(name = "service_password")
    private String servicePassword;

    @Column(nullable = false)
    private boolean isActive = true;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = true)
    private LocalDateTime modifiedAt;

    private LocalDateTime deletedAt;
}
