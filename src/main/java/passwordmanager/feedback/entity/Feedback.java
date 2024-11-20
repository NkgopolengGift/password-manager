package passwordmanager.feedback.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import passwordmanager.users.entity.User;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "Feedback")
@Data
@NoArgsConstructor
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID feedbackId;

    private String content;
    private boolean isRead=false;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private  LocalDateTime createdAt;
    private  UUID createdBy;
}
