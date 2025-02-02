package service;

import models.Recommendation;
import models.User;
import org.springframework.stereotype.Service;
import repositories.interfaces.IRecommendationRepository;
import service.interfaces.IRecommendationService;
import java.util.List;
import java.util.Set;

@Service
public class RecommendationService implements IRecommendationService {
    private final IRecommendationRepository recommendationRepository;

    public RecommendationService(IRecommendationRepository recommendationRepository) {
        this.recommendationRepository = recommendationRepository;
    }

    @Override
    public Recommendation createRecommendation(User fromUser, User toUser, Set<String> items) {
        Recommendation recommendation = new Recommendation(fromUser, toUser, items);
        return recommendationRepository.save(recommendation);
    }

    @Override
    public List<Recommendation> getRecommendationsForUser(User toUser) {
        return recommendationRepository.findByToUser(toUser);
    }
}
