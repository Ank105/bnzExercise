package com.customer.feedback.controller;

import com.customer.feedback.service.OAuth2TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class OAuth2TokenController {

    private final OAuth2TokenService tokenService;

    @Autowired
    public OAuth2TokenController(OAuth2TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @GetMapping("/token")
    public String getToken() {
        return tokenService.getAccessToken();
    }
}

