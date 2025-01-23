package controllers;

import controllers.interfaces.iUserController;
import models.User;
import repositories.interfaces.iUserRepository;

public class UserController implements iUserController {
    private final iUserRepository repo;

    public UserController(iUserRepository repo) {
        this.repo = repo;
    }

    public String createUser(String username) {
        User user = new User(username);

        boolean created = repo.createUser(user);

        return (created ? "User created" : "User was not created");
    }

    public String getUser(int id) {
        User user = repo.getUser(id);

        return (user == null ? "User was not found" : user.toString());
    }
}
