
package controllers;

import controllers.interfaces.IRecommendationController;
import models.Recommendation;
import models.User;
import service.interfaces.IRecommendationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/recommendations")
public abstract class RecommendationController implements IRecommendationController {
    private final IRecommendationService recommendationService;

    public RecommendationController(IRecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

    @PostMapping("/create")
    public Recommendation createRecommendation(@RequestParam int fromUserId,
                                               @RequestParam int toUserId,
                                               @RequestBody Set<String> items) {
        User fromUser = new User();
        fromUser.setID(fromUserId);

        User toUser = new User();
        toUser.setID(toUserId);

        return recommendationService.createRecommendation(fromUser, toUser, items);
    }

    @GetMapping("/user/{userId}")
    public List<Recommendation> getRecommendationsForUser(@PathVariable int userId) {
        User user = new User();
        user.setID(userId);

        return recommendationService.getRecommendationsForUser(user);
    }
}


