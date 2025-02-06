package repositories;

import data.interfaces.IDB;
import models.Artist;
import repositories.interfaces.iArtistRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArtistRepository implements iArtistRepository {
    private final IDB db;

    public ArtistRepository(IDB db) {
        this.db = db;
    }

    @Override
    public boolean createArtist(Artist artist) {
        String query = "INSERT INTO artists (name, country) VALUES (?, ?)";
        try (Connection connection = db.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, artist.getName());
            stmt.setString(2, artist.getCountry());
            int rowsAffected = stmt.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Artist getArtist(int id) {
        String query = "SELECT * FROM artists WHERE id = ?";
        try (Connection connection = db.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Artist(rs.getInt("id"), rs.getString("name"), rs.getString("country"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Artist> getAllArtists() {
        String query = "SELECT * FROM artists";
        List<Artist> artists = new ArrayList<>();
        try (Connection connection = db.getConnection();
             Statement stmt = connection.createStatement()) {

            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Artist artist = new Artist(rs.getInt("id"), rs.getString("name"), rs.getString("country"));
                artists.add(artist);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return artists;
    }

    @Override
    public Artist getArtistByName(String artistName) {
        String query = "SELECT * FROM artists WHERE name = ?";
        try (Connection connection = db.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, artistName);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Artist(rs.getInt("id"), rs.getString("name"), rs.getString("country"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}