package repositories;

import data.interfaces.IDB;
import models.Album;
import repositories.interfaces.iAlbumRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlbumRepository implements iAlbumRepository {
    private final IDB db;

    public AlbumRepository(IDB db) {
        this.db = db;
    }

    @Override
    public boolean createAlbum(Album album) {
        String query = "INSERT INTO albums (artistid, title, releasedate, genre) VALUES (?, ?, ?, ?)";
        try (Connection connection = db.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, album.getArtistId());
            stmt.setString(2, album.getTitle());
            stmt.setDate(3, album.getReleaseDate());
            stmt.setString(4, album.getGenre());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Album getAlbum(int id) {
        String query = "SELECT * FROM albums WHERE id = ?";
        try (Connection connection = db.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Album(rs.getInt("id"),
                        rs.getInt("artistid"),
                        rs.getString("title"),
                        rs.getDate("releasedate"),
                        rs.getString("genre"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Album> getAllAlbums() {
        String query = "SELECT * FROM albums";
        List<Album> albums = new ArrayList<>();
        try (Connection connection = db.getConnection();
             Statement stmt = connection.createStatement()) {

            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Album album = new Album(rs.getInt("id"),
                        rs.getInt("artistid"),
                        rs.getString("title"),
                        rs.getDate("releasedate"),
                        rs.getString("genre"));
                albums.add(album);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return albums;
    }

    @Override
    public int getAlbumIdByTitle(String title) {
        String query = "SELECT id FROM albums WHERE title = ?";
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
    public Album getAlbumByTitle(String title) {
        try (Connection connection = db.getConnection()) {
            String sql = "SELECT * FROM albums WHERE title = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, title);
                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    int artistId = resultSet.getInt("artistid");
                    String albumTitle = resultSet.getString("title");
                    Date releaseDate = resultSet.getDate("releasedate");
                    String genre = resultSet.getString("genre");

                    return new Album(id, artistId, albumTitle, releaseDate, genre);
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Album getAlbumWithArtistName(String title) {
        String query = "SELECT a.id, a.artistid, a.title, a.releasedate, a.genre, ar.name as artist_name " +
                "FROM albums a " +
                "JOIN artists ar ON a.artistid = ar.id " +
                "WHERE a.title = ?";
        try (Connection connection = db.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, title);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Album album = new Album(rs.getInt("id"),
                        rs.getInt("artistid"),
                        rs.getString("title"),
                        rs.getDate("releasedate"),
                        rs.getString("genre"));
                album.setArtistName(rs.getString("artist_name"));
                return album;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
