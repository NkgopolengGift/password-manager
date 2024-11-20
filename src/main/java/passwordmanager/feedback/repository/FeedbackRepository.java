package passwordmanager.feedback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import passwordmanager.feedback.entity.Feedback;

import java.util.UUID;

public interface FeedbackRepository extends JpaRepository<Feedback, UUID> {
}
