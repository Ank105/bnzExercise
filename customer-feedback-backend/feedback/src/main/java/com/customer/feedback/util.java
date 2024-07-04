package com.customer.feedback;

public class util {

    public enum Rating {
        VERY_UNHAPPY("Very Unhappy"),
        MODERATELY_UNHAPPY("Moderately Unhappy"),
        NEUTRAL("Neutral"),
        MODERATELY_HAPPY("Moderately Happy"),
        VERY_HAPPY("Very Happy");

        private String value;

        Rating(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
        }

}
