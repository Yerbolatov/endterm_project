package repositories;

import data.interfaces.IDB;
import models.User;
import repositories.interfaces.iUserRepository;

import java.sql.*;


public class UserRepository implements iUserRepository {
    private final IDB db;

    public UserRepository(IDB db) {
        this.db = db;
    }

@Override
public boolean createUser(User user) {
    Connection con = null;

    try {
        con = db.getConnection();
        String sql = "INSERT INTO public.users(username) VALUES (?)";
        PreparedStatement st = con.prepareStatement(sql);

        st.setString(1, user.getUsername());

        st.execute();

        return true;
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
    return false;
}

@Override
public User getUser(int id) {
    Connection con = null;

    try {
        con = db.getConnection();
        String sql ="SELECT id, username FROM public.users WHERE id = ?";
        PreparedStatement st = con.prepareStatement(sql);

        st.setInt(1, id);

        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            return new User(rs.getInt("id"), rs.getString("username"));
        }
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
    return null;
    }
}