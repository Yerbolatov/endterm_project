
package controllers.interfaces;

import models.Recommendation;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

public interface IRecommendationController {
    Recommendation createRecommendation(int fromUserId, int toUserId, Set<String> items);
    List<Recommendation> getRecommendationsForUser(int userId);
}


