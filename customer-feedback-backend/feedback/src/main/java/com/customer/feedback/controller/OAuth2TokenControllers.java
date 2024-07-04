package com.customer.feedback.controller;


    import com.customer.feedback.service.OAuth2TokenService;
    import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

    @Controller
    public class OAuth2TokenControllers {

        private final OAuth2TokenService tokenService;

        @Autowired
        public OAuth2TokenControllers(OAuth2TokenService tokenService) {
            this.tokenService = tokenService;
        }

        @PostMapping("/oauth/token")  // Endpoint mapping for OAuth2 token request
        @ResponseBody
        public ResponseEntity<String> getToken() {
            String accessToken = tokenService.getAccessToken();
            return ResponseEntity.ok(accessToken);
        }
    }


