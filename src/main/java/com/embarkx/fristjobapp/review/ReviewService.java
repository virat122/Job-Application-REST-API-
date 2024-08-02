package com.embarkx.fristjobapp.review;

import java.util.List;

public interface ReviewService {
    List<Review> getAllReview(Long companyId);
    boolean addReview(Long companyId,Review review);
    Review getReview(Long companyId,Long reviewId);
    boolean updateReview(Long companyId,Long reviewId,Review review);
    boolean deleteReviewById(Long companyId,Long reviewId);

}
