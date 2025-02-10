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
        if (username == null || username.trim().isEmpty()) return "Username cannot be empty";
        if (username.length() < 3) return "Username must be at least 3 characters long";

        User user = new User(username);
        boolean created = repo.createUser(user);
        return created ? "User created" : "User was not created";
    }

    public String getUser(int id) {
        User user = repo.getUser(id);

        return (user == null ? "User was not found" : user.toString());
    }
}
