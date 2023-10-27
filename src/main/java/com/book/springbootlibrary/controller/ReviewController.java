package com.book.springbootlibrary.controller;

import com.book.springbootlibrary.requestmodels.ReviewRequest;
import com.book.springbootlibrary.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("https://localhost:3000")
@RequestMapping("/api/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    @Autowired
    public ReviewController (ReviewService reviewService){
        this.reviewService = reviewService;
    }

    @PostMapping("/secure")
    public void postReview(JwtAuthenticationToken token, @RequestBody ReviewRequest reviewRequest) throws Exception{
        String userEmail = token.getToken().getSubject();
        if (userEmail == null){
            throw new Exception("user email is missing!");
        }
        reviewService.postReview(userEmail, reviewRequest);
    }

    @GetMapping("/secure/user/book")
    public Boolean reviewBookByUser(JwtAuthenticationToken token, @RequestParam Long bookId) throws Exception{
        String userEmail = token.getToken().getSubject();
        if (userEmail == null){
            throw new Exception("user email is missing!");
        }
        return reviewService.userReviewListed(userEmail, bookId);
    }


}
