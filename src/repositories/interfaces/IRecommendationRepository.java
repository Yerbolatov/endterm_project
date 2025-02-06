
package repositories.interfaces;

import models.Recommendation;
import models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IRecommendationRepository extends JpaRepository<Recommendation, Long> {
    List<Recommendation> findByToUser(User toUser);
}


