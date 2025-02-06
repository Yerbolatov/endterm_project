package service.interfaces;

import java.sql.SQLException;

public interface iUserLogin {
    boolean loginUser(String username, String password) throws SQLException;
}