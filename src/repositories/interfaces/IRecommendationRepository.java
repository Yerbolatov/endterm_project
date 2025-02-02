package repositories.interfaces;

import models.Recommendation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRecommendationRepository extends JpaRepository<Recommendation, Long> {
}
