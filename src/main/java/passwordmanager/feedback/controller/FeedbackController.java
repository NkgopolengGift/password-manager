package passwordmanager.feedback.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import passwordmanager.feedback.entity.Feedback;
import passwordmanager.feedback.service.FeedbackService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    // Create Feedback
    @PostMapping
    public Feedback createFeedback(@RequestBody Feedback feedback) {
        return feedbackService.createFeedback(feedback);
    }

    // Update Feedback (set isRead = true)
    @PatchMapping("/{id}/mark-as-read")
    public Feedback markAsRead(@PathVariable UUID id) {
        return feedbackService.markAsRead(id);
    }

    // Fetch All Feedback
    @GetMapping
    public List<Feedback> getAllFeedback() {
        return feedbackService.getAllFeedback();
    }
}
