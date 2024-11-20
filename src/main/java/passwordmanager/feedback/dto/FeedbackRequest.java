package passwordmanager.feedback.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class    FeedbackRequest {

    private UUID feedbackId;
    private String content;
    private boolean isRead;
    private LocalDateTime createdAt;
    private  UUID createdBy;
}
