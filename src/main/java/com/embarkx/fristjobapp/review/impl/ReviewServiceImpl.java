package com.embarkx.fristjobapp.review.impl;

import com.embarkx.fristjobapp.company.Company;
import com.embarkx.fristjobapp.company.CompanyService;
import com.embarkx.fristjobapp.review.Review;
import com.embarkx.fristjobapp.review.ReviewRepository;
import com.embarkx.fristjobapp.review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl  implements ReviewService {
private  final ReviewRepository reviewRepository;
private final CompanyService companyService;

    public ReviewServiceImpl(ReviewRepository reviewRepository
                             ,CompanyService companyService) {
        this.reviewRepository = reviewRepository;
        this.companyService=companyService;
    }

    @Override
    public List<Review> getAllReview(Long companyId) {
        List<Review> reviews=reviewRepository.findByCompanyId(companyId);
        return reviews;
    }

    @Override
    public boolean addReview(Long companyId, Review review) {
        Company company=companyService.getCompanyById(companyId);
        if(company!=null){
            review.setCompany(company);
            reviewRepository.save(review);
            return true;

        }else {
            return false;
        }

    }

    @Override
    public Review getReview(Long companyId, Long reviewId) {
        List<Review> reviews=reviewRepository.findByCompanyId(companyId);

        return reviews.stream()
                .filter(review -> review.getId().equals(reviewId))
                .findFirst()
                .orElse(null);


    }

    @Override
    public boolean updateReview(Long companyId, Long reviewId, Review updatedReview) {

        if(companyService.getCompanyById(companyId)!=null){
            // set (attach )company into updated review
            updatedReview.setCompany(companyService.getCompanyById(companyId));
            updatedReview.setId(reviewId);
            reviewRepository.save(updatedReview);
            return true;
        }else {
            return false;
        }

    }

    @Override
    public boolean deleteReviewById(Long companyId, Long reviewId) {


/*       if(companyService.getCompanyById(companyId)!=null
               && reviewRepository.existsById(reviewId))

               this condition may cause user want to delete review id of different companyshowing internal server error
 */



        if(getReview(companyId,reviewId)!=null){

            Review review=reviewRepository.findById(reviewId).orElse(null);

            Company company=review.getCompany();
            company.getReviews().remove(review);
            //review.setCompany(null);
            companyService.updateCompany(company,companyId);

            reviewRepository.deleteById(reviewId);
            return true;

        }else {
            return false;
        }

    }


}
