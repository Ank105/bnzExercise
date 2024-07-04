package com.customer.feedback.service;


import com.customer.feedback.dto.FeedbackRequest;
import com.customer.feedback.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class FeedbackService {

    private FeedbackRepository feedbackRepository;

    @Autowired
    public FeedbackService(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    public FeedbackRequest saveFeedback(FeedbackRequest feedback) {
        return feedbackRepository.save(feedback);
    }

    public Optional<FeedbackRequest> getFeedbackById(Long feedbackId) {
        return feedbackRepository.findById(feedbackId);
    }

}
