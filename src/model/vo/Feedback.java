package model.vo;

import java.time.LocalDateTime;

public class Feedback {

    private String feedback;
    private LocalDateTime feedbackTime;

    public Feedback(String feedback, LocalDateTime feedbackTime) {
        this.feedback = feedback;
        this.feedbackTime = feedbackTime;
    }

    public Feedback() {}

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public LocalDateTime getFeedbackTime() {
        return feedbackTime;
    }

    public void setFeedbackTime(LocalDateTime feedbackTime) {
        this.feedbackTime = feedbackTime;
    }
}
