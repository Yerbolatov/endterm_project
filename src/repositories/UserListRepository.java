package repositories;

import data.interfaces.IDB;
import models.Album;
import models.UserList;
import repositories.interfaces.iUserListRepository;

import java.sql.*;
import java.util.*;

public class UserListRepository implements iUserListRepository {
    private final IDB db;

    public UserListRepository(IDB db) {
        this.db = db;
    }

    @Override
    public boolean createList(String title, String createdBy) {
        String query = "INSERT INTO userlists (title, createdby) VALUES (?, ?)";
        try (Connection con = db.getConnection();
             PreparedStatement st = con.prepareStatement(query)) {
            st.setString(1, title);
            st.setString(2, createdBy);
            return st.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean addAlbumToList(int listId, int albumId, String comment, String username) {
        String ownershipQuery = "SELECT createdby FROM userlists WHERE id = ?";
        String insertQuery = "INSERT INTO listalbums (listid, albumid, comment) VALUES (?, ?, ?)";

        try (Connection con = db.getConnection();
             PreparedStatement ownershipStmt = con.prepareStatement(ownershipQuery);
             PreparedStatement insertStmt = con.prepareStatement(insertQuery)) {

            ownershipStmt.setInt(1, listId);
            ResultSet rs = ownershipStmt.executeQuery();

            if (rs.next()) {
                String owner = rs.getString("createdby");
                if (!owner.equals(username)) {
                    System.out.println("You are not the owner of this list.");
                    return false;
                }
            } else {
                System.out.println("List not found.");
                return false;
            }

            insertStmt.setInt(1, listId);
            insertStmt.setInt(2, albumId);
            insertStmt.setString(3, comment);
            return insertStmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public UserList getListById(int id) {
        String query = "SELECT * FROM userlists WHERE id = ?";
        try (Connection con = db.getConnection();
             PreparedStatement st = con.prepareStatement(query)) {
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new UserList(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("createdby"),
                        new ArrayList<>(),
                        new HashMap<>()
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<UserList> getListsByUser(String username) {
        List<UserList> lists = new ArrayList<>();
        String query = "SELECT * FROM userlists WHERE createdby = ?";
        try (Connection con = db.getConnection();
             PreparedStatement st = con.prepareStatement(query)) {
            st.setString(1, username);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                UserList list = new UserList(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("createdby"),
                        new ArrayList<>(),
                        new HashMap<>()
                );
                lists.add(list);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return lists;
    }

    @Override
    public int getListIdByTitle(String title) {
        String query = "SELECT id FROM userlists WHERE title = ?";
        try (Connection conn = db.getConnection();
             PreparedStatement st = conn.prepareStatement(query)) {
            st.setString(1, title);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public Integer getAlbumIdByTitle(String title) {
        String query = "SELECT id FROM albums WHERE title = ?";
        try (Connection connection = db.getConnection();
             PreparedStatement st = connection.prepareStatement(query)) {
            st.setString(1, title);
            ResultSet rs = st.executeQuery();
            return rs.next() ? rs.getInt("id") : null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


}
