package controllers;

import controllers.interfaces.iReviewController;
import models.Review;
import repositories.interfaces.iReviewRepository;

import java.util.List;

public class ReviewController implements iReviewController {
    private final iReviewRepository repo;

    public ReviewController(iReviewRepository repo) {
        this.repo = repo;
    }

    public String addReview(int albumId, int userId, String text, double rating) {
        if (albumId <= 0 || userId <= 0) return "Invalid album or user ID";
        if (text == null || text.trim().isEmpty()) return "Review text cannot be empty";
        if (rating < 0 || rating > 10) return "Rating must be between 0 and 10";

        Review review = new Review(0, albumId, userId, text, rating);
        boolean added = repo.addReview(review);
        return added ? "Review added" : "Review was not added";
    }

    public String deleteReview(int reviewId) {
        boolean deleted = repo.deleteReview(reviewId);
        return deleted ? "Review deleted successfully" : "Failed to delete review";
    }

    public List<Review> getReviewsByAlbum(int albumId) {
        return repo.getReviewsByAlbum(albumId);
    }

    public List<Review> getReviewsByUser(int userId) {
        return repo.getReviewsByUser(userId);
    }
}
