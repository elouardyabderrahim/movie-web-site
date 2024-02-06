package com.api.movies.service;

import com.api.movies.entities.Movie;
import com.api.movies.entities.Review;
import com.api.movies.repository.ReviewRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;

public class ReviewService {

    private final ReviewRepository reviewService;
    private final MongoTemplate mongoTemplate;

    public ReviewService(MovieService movieService, ReviewRepository reviewService, MongoTemplate mongoTemplate) {
        this.reviewService = reviewService;
        this.mongoTemplate = mongoTemplate;
    }

    public Review createReview(String reviewBody, String imdbId) {
        // Review review = new Review(reviewBody);
        //reviewService.insert(review);

        Review review = reviewService.insert(new Review(reviewBody));


        mongoTemplate.update(Movie.class)
                .matching(Criteria.where("imdbId").is(imdbId))
                .apply(new Update().push("reviewIds").value(review))
                .first();

        return review;

    }
}
