package models.interfaces;

import models.User;
import java.util.Set;

public interface IRecommendation {
    Long getId();
    User getFromUser();
    User getToUser();
    Set<String> getRecommendedItems();
}
