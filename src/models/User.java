package models;

import lombok.Getter;
import lombok.Setter;

public class User {
    private int id;
    @Getter
    @Setter
    private String username;
    @Getter
    @Setter
    public String role;

    public User() {}

    public User(String username, String role) {
        setUsername(username);
        setRole(role);
    }
    public User(int id, String username, String role) {
        this(username, role);
        setID(id);
    }

    public int getID() {
        return id;
    }
    public void setID(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                '}';
    }
}