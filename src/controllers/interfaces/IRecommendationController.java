package controllers.interfaces;

import models.Recommendation;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

public interface IRecommendationController {
    Recommendation createRecommendation(Long fromUserId, Long toUserId, Set<String> items);
    List<Recommendation> getRecommendationsForUser(Long userId);
}
