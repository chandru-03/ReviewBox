package com.reviewBox.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.reviewBox.entity.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
    List<Review> findByReviewBoxId(int movieId);
    
    @Query("SELECT COALESCE(AVG(r.rating),0) FROM Review r WHERE r.reviewBox.id = :movieId ")
    Double findAverageRatingByMovieId(@Param("movieId") int movieId);
}