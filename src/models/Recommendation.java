
package models;

import jakarta.persistence.*;
import models.interfaces.IRecommendation;
import java.util.Set;

@Entity
public class Recommendation implements IRecommendation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private User fromUser;

    @ManyToOne
    private User toUser;

    @ElementCollection
    private Set<String> recommendedItems;

    public Recommendation() {}

    public Recommendation(User fromUser, User toUser, Set<String> recommendedItems) {
        this.fromUser = fromUser;
        this.toUser = toUser;
        this.recommendedItems = recommendedItems;
    }

    @Override
    public int getID() {
        return id;
    }

    @Override
    public User getFromUser() {
        return fromUser;
    }

    @Override
    public User getToUser() {
        return toUser;
    }

    @Override
    public Set<String> getRecommendedItems() {
        return recommendedItems;
    }
}

