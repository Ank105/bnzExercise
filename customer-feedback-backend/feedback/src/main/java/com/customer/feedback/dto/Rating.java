package com.customer.feedback.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Rating {
    VERY_UNHAPPY("Very Unhappy"),
    MODERATELY_UNHAPPY("Moderately Unhappy"),
    NEUTRAL("Neutral"),
    MODERATELY_HAPPY("Moderately Happy"),
    VERY_HAPPY("Very Happy");

    private final String value;

    Rating(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static Rating fromString(String text) {
        for (Rating rating : Rating.values()) {
            if (rating.value.equalsIgnoreCase(text)) {
                return rating;
            }
        }
        throw new IllegalArgumentException("No constant with text " + text + " found");
    }
}
