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
}
