package com.customer.feedback.repository;


import com.customer.feedback.dto.FeedbackRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackRepository extends JpaRepository<FeedbackRequest, Long> {
}