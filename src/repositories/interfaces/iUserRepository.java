package repositories.interfaces;

import models.User;

public interface iUserRepository {
    boolean createUser(User user);
    User getUser(int id);
}
