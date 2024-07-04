package com.customer.feedback.service;

import com.customer.feedback.dto.FeedbackRequest;
import com.customer.feedback.dto.Rating;
import com.customer.feedback.repository.FeedbackRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class FeedbackServiceTest {

    @Mock
    private FeedbackRepository feedbackRepository;

    @InjectMocks
    private FeedbackService feedbackService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSaveFeedback() {
        // Create a mock FeedbackRequest
        FeedbackRequest mockRequest = new FeedbackRequest();
        mockRequest.setId(1L);
        mockRequest.setComment("Test comment");
        mockRequest.setRating("Very Happy");
        mockRequest.setEmail("test@example.com");

        // Mock behavior of feedbackRepository.save()
        when(feedbackRepository.save(any(FeedbackRequest.class))).thenReturn(mockRequest);

        // Call the service method
        FeedbackRequest savedFeedback = feedbackService.saveFeedback(mockRequest);

        // Assert that the saved feedback matches the mockRequest
        Assertions.assertEquals(mockRequest.getId(), savedFeedback.getId());
        Assertions.assertEquals(mockRequest.getComment(), savedFeedback.getComment());
        Assertions.assertEquals(mockRequest.getRating(), savedFeedback.getRating());
        Assertions.assertEquals(mockRequest.getEmail(), savedFeedback.getEmail());
    }

    @Test
    public void testGetFeedbackById() {
        // Create a mock FeedbackRequest
        FeedbackRequest mockRequest = new FeedbackRequest();
        mockRequest.setId(1L);
        mockRequest.setComment("Test comment");
        mockRequest.setRating("Very Happy");
        mockRequest.setEmail("test@example.com");

        // Mock behavior of feedbackRepository.findById()
        when(feedbackRepository.findById(1L)).thenReturn(Optional.of(mockRequest));

        // Call the service method
        Optional<FeedbackRequest> retrievedFeedback = feedbackService.getFeedbackById(1L);

        // Assert that feedback is present and matches the mockRequest
        Assertions.assertTrue(retrievedFeedback.isPresent());
        retrievedFeedback.ifPresent(feedback -> {
            Assertions.assertEquals(mockRequest.getId(), feedback.getId());
            Assertions.assertEquals(mockRequest.getComment(), feedback.getComment());
            Assertions.assertEquals(mockRequest.getRating(), feedback.getRating());
            Assertions.assertEquals(mockRequest.getEmail(), feedback.getEmail());
        });
    }

    @Test
    public void testGetFeedbackById_NotFound() {
        // Mock behavior of feedbackRepository.findById() for a non-existing ID
        when(feedbackRepository.findById(2L)).thenReturn(Optional.empty());

        // Call the service method
        Optional<FeedbackRequest> retrievedFeedback = feedbackService.getFeedbackById(2L);

        // Assert that no feedback is present
        Assertions.assertFalse(retrievedFeedback.isPresent());
    }
}
