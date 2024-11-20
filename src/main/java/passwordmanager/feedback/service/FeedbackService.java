package passwordmanager.feedback.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import passwordmanager.feedback.entity.Feedback;
import passwordmanager.feedback.repository.FeedbackRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    // Create Feedback
    public Feedback createFeedback(Feedback feedback) {
        return feedbackRepository.save(feedback);
    }

    // Update Feedback (set isRead = true)
    public Feedback markAsRead(UUID feedbackId) {
        Optional<Feedback> optionalFeedback = feedbackRepository.findById(feedbackId);
        if (optionalFeedback.isPresent()) {
            Feedback feedback = optionalFeedback.get();
            feedback.setRead(true);
            return feedbackRepository.save(feedback);
        } else {
            throw new RuntimeException("Feedback not found");
        }
    }

    // Fetch All Feedback
    public List<Feedback> getAllFeedback() {
        return feedbackRepository.findAll();
    }
}
