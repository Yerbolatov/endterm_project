package controllers.interfaces;

import models.Review;
import java.util.List;

public interface iReviewController {
    String addReview(int userId, int albumId, String content, double rating);
    String deleteReview(int reviewId);
    List<Review> getReviewsByAlbum(int albumId);
    List<Review> getReviewsByUser(int userId);
}
