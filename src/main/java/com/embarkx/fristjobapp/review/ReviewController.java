package com.embarkx.fristjobapp.review;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}")
public class ReviewController {
    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }


    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> getAllReview(@PathVariable Long companyId){
        return new ResponseEntity<>(reviewService.getAllReview(companyId), HttpStatus.OK);
    }

    @PostMapping("/reviews")
    public ResponseEntity<String> addReview(@PathVariable Long companyId,
                                            @RequestBody Review review){
       boolean isAdded= reviewService.addReview(companyId,review);
       if(isAdded){
           return new ResponseEntity<>("review added succesfully",HttpStatus.OK);
       }else {
           return new ResponseEntity<>(" company not exist so review not added",HttpStatus.NOT_FOUND);

       }


    }
    @GetMapping("reviews/{reviewId}")
    public ResponseEntity<Review>getReview(@PathVariable Long companyId,
                                           @PathVariable Long reviewId){

        Review review=reviewService.getReview(companyId,reviewId);
        if(review!=null){
            return new ResponseEntity<>(review, HttpStatus.OK);

        }else {
           // System.out.println("review is Null");
            return new ResponseEntity<>(review, HttpStatus.NOT_FOUND);
        }

    }
    @PutMapping("/reviews/{reviewId}")
    public ResponseEntity<String > updateReview(@PathVariable Long companyId,
                                                @PathVariable Long reviewId,
                                                @RequestBody Review updatedReview){
        boolean isReviewUpdated= reviewService.updateReview(companyId, reviewId,updatedReview);
        if(isReviewUpdated){
            return new ResponseEntity<>("review Updated sucessfully",HttpStatus.OK);
        }else {
            return new ResponseEntity<>("review not  Updated ",HttpStatus.NOT_FOUND);
        }





    }


@DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<String > deleteReviewById(@PathVariable Long companyId,
                                                    @PathVariable Long reviewId){

        boolean isReviewDeleted= reviewService.deleteReviewById(companyId,reviewId);

        if(isReviewDeleted){
            return new ResponseEntity<>("Review Deleted Sucessfully",HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Review  is not Deleted ",HttpStatus.NOT_FOUND);
        }


}

}
