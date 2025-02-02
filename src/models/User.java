package models;

public class User {
    private int id;
    private String username;

    public User() {

    }

    public User(String username) {
        setUsername(username);
    }
    public User(int id, String username) {
        this(username);
        setID(id);
    }

    public int getID() {
        return id;
    }
    public void setID(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                '}';
    }
}