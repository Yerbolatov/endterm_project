package repositories;

import data.interfaces.IDB;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import repositories.interfaces.iUserLogin;

import java.sql.*;

public class UserLogin implements iUserLogin {
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final IDB db;

    public UserLogin(IDB db) {
        this.db = db;
    }

    @Override
    public boolean loginUser(String username, String password) throws SQLException {
        String query = "SELECT passwordhash FROM users WHERE username = ?";
        try (Connection connection = db.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String storedHash = resultSet.getString("passwordhash");
                if (passwordEncoder.matches(password, storedHash)) {
                    System.out.println("Login successful!");
                    return true;
                } else {
                    System.out.println("Invalid password.");
                }
            } else {
                System.out.println("User not found.");
            }
        }
        return false;
    }
}