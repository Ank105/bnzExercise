package com.customer.feedback.controller;


import com.customer.feedback.dto.FeedbackRequest;
import com.customer.feedback.dto.FeedbackResponse;
import com.customer.feedback.service.FeedbackService;
import com.customer.feedback.service.OAuth2TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@RestController
@RequestMapping("/api/")
@CrossOrigin("http://192.168.0.102:8081")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @Autowired
    private OAuth2TokenService tokenService;


    private RestTemplate restTemplate;

    @PostMapping("/feedback")
    public ResponseEntity<Object> saveFeedback(@RequestBody FeedbackRequest feedbackRequest) {
         var x=feedbackService.saveFeedback(feedbackRequest);
        FeedbackResponse feedbackRequestId = new FeedbackResponse(x.getId());
         return new ResponseEntity<>(feedbackRequestId,HttpStatus.CREATED);
    }

    @GetMapping("/{feedbackId}")
    public FeedbackRequest getFeedbackById(@PathVariable Long feedbackId) throws Exception {
        Optional<FeedbackRequest> feedback = feedbackService.getFeedbackById(feedbackId);
        return feedback.orElseThrow(() -> new Exception("Feedback not found with ID: " + feedbackId));
    }

   /* @GetMapping("/{feedbackId}")
    public FeedbackRequest getFeedbackById(@PathVariable Long feedbackId) throws Exception {
        // Obtain access token
        String accessToken = tokenService.getAccessToken();

        // Prepare headers with Authorization Bearer token
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        // Make GET request with authenticated headers
        ResponseEntity<FeedbackRequest> response = restTemplate.exchange(
                "http://localhost:8080/api/" + feedbackId, // Replace with your API endpoint
                HttpMethod.GET,
                entity,
                FeedbackRequest.class);

        return response.getBody();
    }*/
}