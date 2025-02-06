
package models.interfaces;

import models.User;
import java.util.Set;

public interface IRecommendation {
    int getID();
    User getFromUser();
    User getToUser();
    Set<String> getRecommendedItems();
}


