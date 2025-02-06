package service.interfaces;

import java.sql.SQLException;

public interface iUserRegister {
    void registerUser(String username, String password) throws SQLException;
}