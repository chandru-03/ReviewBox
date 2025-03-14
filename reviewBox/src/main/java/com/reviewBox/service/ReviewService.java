package com.reviewBox.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reviewBox.entity.Review;
import com.reviewBox.repository.ReviewRepository;

@Service
public class ReviewService {
    
    @Autowired
    private ReviewRepository reviewRepository;

    public void saveReview(Review review) {
        reviewRepository.save(review);
    }

    public List<Review> getReviewsByMovieId(int movieId) {
        return reviewRepository.findByReviewBoxId(movieId);
    }
    
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }
    
    public void deleteById(int id) {
        if (reviewRepository.existsById(id)) {
            reviewRepository.deleteById(id);
        } else {
            throw new RuntimeException("Review not found with ID: " + id);
        }
    }
    
    public Review getReviewById(int id) {
        return reviewRepository.findById(id).orElseThrow(() -> new RuntimeException("Review not found"));
    }
    
    public void updateReview(Review updatedReview) {
        Review existingReview = reviewRepository.findById(updatedReview.getId())
            .orElseThrow(() -> new RuntimeException("Review not found"));

        existingReview.setTitle(updatedReview.getTitle());
        existingReview.setReviewerName(updatedReview.getReviewerName());
        existingReview.setComment(updatedReview.getComment());
        existingReview.setRating(updatedReview.getRating());

        reviewRepository.save(existingReview);
    }
    
    public double getAverageRating(int movieId) {
    	return reviewRepository.findAverageRatingByMovieId(movieId);
    }
}