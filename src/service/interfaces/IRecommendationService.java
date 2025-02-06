
package service.interfaces;

import models.Recommendation;
import models.User;
import java.util.List;
import java.util.Set;

public interface IRecommendationService {
    Recommendation createRecommendation(User fromUser, User toUser, Set<String> items);
    List<Recommendation> getRecommendationsForUser(User toUser);
}
