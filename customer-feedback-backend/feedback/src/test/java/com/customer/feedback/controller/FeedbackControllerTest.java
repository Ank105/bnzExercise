package com.customer.feedback.controller;

import com.customer.feedback.dto.FeedbackRequest;
import com.customer.feedback.dto.FeedbackResponse;
import com.customer.feedback.service.FeedbackService;
import com.customer.feedback.dto.Rating;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(FeedbackController.class)
//@AutoConfigureMockMvc
public class FeedbackControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FeedbackService feedbackService;

    @InjectMocks
    private FeedbackController feedbackController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void testSaveFeedback_ValidRequest() throws Exception {

        FeedbackRequest request = new FeedbackRequest();
        request.setComment("Test feedback");
        request.setRating("Very Happy"); // Valid rating
        request.setEmail("test@example.com");
        request.setId(1L);

       // FeedbackResponse response = new FeedbackResponse(1L);


        when(feedbackService.saveFeedback(any(FeedbackRequest.class))).thenReturn(request);


        mockMvc.perform(MockMvcRequestBuilders.post("/api/feedback")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"comment\": \"Test feedback\", \"rating\": \"Very Happy\", \"email\": \"test@example.com\" }"))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L));
    }

    @Test
    public void testGetFeedbackById_NotFound() {

        when(feedbackService.getFeedbackById(null)).thenReturn(Optional.empty());
        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            feedbackController.getFeedbackById(null);
        });
        String expectedMessage = "Feedback not found with ID: null";
        String actualMessage = exception.getMessage();

        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testGetFeedbackById_Success() throws Exception {
        // Create a mock FeedbackRequest
        FeedbackRequest mockFeedback = new FeedbackRequest();
        mockFeedback.setId(1L);
        mockFeedback.setComment("Test feedback");
        mockFeedback.setEmail("test@example.com");
        mockFeedback.setRating("Very Happy");

        // Mock behavior of feedbackService.getFeedbackById() to return Optional.of(mockFeedback) for ID 1L
        when(feedbackService.getFeedbackById(1L)).thenReturn(Optional.of(mockFeedback));

        // Invoke the controller method with a valid ID
        FeedbackRequest response = feedbackController.getFeedbackById(1L);

        // Verify the returned FeedbackRequest matches the mockFeedback
        Assertions.assertEquals(mockFeedback.getId(), response.getId());
        Assertions.assertEquals(mockFeedback.getComment(), response.getComment());
        Assertions.assertEquals(mockFeedback.getEmail(), response.getEmail());
        Assertions.assertEquals(mockFeedback.getRating(), response.getRating());
    }
}
