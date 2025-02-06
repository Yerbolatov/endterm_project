package service;

import data.interfaces.IDB;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import service.interfaces.iUserRegister;

import java.sql.*;

public class UserRegister implements iUserRegister {
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final IDB db;

    public UserRegister(IDB db) {
        this.db = db;
    }

    @Override
    public void registerUser(String username, String password) throws SQLException {
        String hashedPassword = passwordEncoder.encode(password);
        String defaultRole = "user";

        String query = "INSERT INTO users (username, passwordhash, role) VALUES (?, ?, ?)";
        try (Connection connection = db.getConnection();
             PreparedStatement st = connection.prepareStatement(query)) {
            st.setString(1, username);
            st.setString(2, hashedPassword);
            st.setString(3, defaultRole);
            st.executeUpdate();
            System.out.println("User registered successfully!");
        } catch (SQLException e) {
            if (e.getMessage().contains("duplicate key")) {
                System.out.println("Error: Username already exists!");
            } else {
                throw e;
            }
        }
    }
}