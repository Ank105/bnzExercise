package com.customer.feedback.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class OAuth2TokenService {

    private final RestTemplate restTemplate;

    @Value("${spring.security.oauth2.client.registration.my-client.client-id}")
    private String clientId;
//
    @Value("${spring.security.oauth2.client.registration.my-client.client-secret}")
    private String clientSecret;

    @Value("${spring.security.oauth2.client.registration.my-client.scope}")
    private String scope;
//
    @Value("${spring.security.oauth2.client.provider.bnz.token-uri}")
    private String tokenUri;

    private String accessToken;

    @Autowired
    public OAuth2TokenService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getAccessToken() {
        if (accessToken == null) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            // Construct the request body
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(tokenUri)
                    .queryParam("grant_type", "client_credentials")
                    .queryParam("client_id", clientId)
                    .queryParam("client_secret", clientSecret)
                    .queryParam("scope", scope);;

            HttpEntity<String> entity = new HttpEntity<>(headers);

            // Make the POST request
            ResponseEntity<String> response = restTemplate.exchange(
                    builder.toUriString(),
                    HttpMethod.POST,
                    entity,
                    String.class);

            // Extract and store the access token
            if (response.getStatusCode() == HttpStatus.OK) {
                // Assuming the response body contains the access token JSON
                // Parse the response JSON to get the access token
                // Replace with actual JSON parsing logic based on your OAuth2 provider's response format
                accessToken = extractAccessToken(response.getBody());
            } else {
                throw new RuntimeException("Failed to retrieve access token: " + response.getStatusCode());
            }
        }
        return accessToken;
    }

    private String extractAccessToken(String responseBody) {
        // Implement logic to extract access token from JSON response
        // Replace with actual JSON parsing logic based on your OAuth2 provider's response format
        return "your-access-token";
    }
}
