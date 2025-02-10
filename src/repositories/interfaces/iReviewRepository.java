package repositories.interfaces;

import models.Review;
import java.util.List;

public interface iReviewRepository {
    boolean addReview(Review review);
    boolean deleteReview(int reviewId);
    List<Review> getReviewsByAlbum(int albumId);
    List<Review> getReviewsByUser(int userId);
}
