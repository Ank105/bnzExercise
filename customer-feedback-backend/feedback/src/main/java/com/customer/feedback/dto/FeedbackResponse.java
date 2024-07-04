package com.customer.feedback.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeedbackResponse {

    private Long id;

    public FeedbackResponse(Long id) {
        this.id = id;
    }
}
