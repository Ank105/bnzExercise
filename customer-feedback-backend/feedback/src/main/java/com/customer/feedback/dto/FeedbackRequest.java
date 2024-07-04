package com.customer.feedback.dto;

import com.customer.feedback.util;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FeedbackRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String comment;

//    @NotNull(message = "Rating is mandatory")
//    @Enumerated(EnumType.STRING)
    private String rating;
    private String email;


}