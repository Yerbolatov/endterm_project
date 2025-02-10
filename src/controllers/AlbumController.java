package controllers;

import controllers.interfaces.iAlbumController;

import models.Album;
import repositories.interfaces.iAlbumRepository;

import java.time.LocalDate;
import java.sql.Date;
import java.util.List;

public class AlbumController implements iAlbumController {
    private final iAlbumRepository repo;

    public AlbumController(iAlbumRepository repo) {
        this.repo = repo;
    }

    public String createAlbum(int artistId, String title, String releaseDate, String genre) {
        try {
            LocalDate localDate = LocalDate.parse(releaseDate);

            Date sqlDate = Date.valueOf(localDate);

            Album album = new Album(0, artistId, title, sqlDate, genre);

            boolean created = repo.createAlbum(album);
            return created ? "Album created" : "Album was not created";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    public String getAlbum(int id) {
        Album album = repo.getAlbum(id);
        return album == null ? "Album was not found" : album.toString();
    }

    public String getAlbumArtist(int artistId) {
        Album album = repo.getAlbum(artistId);
        return album == null ? "Artist was not found" : album.toString();
    }

    public List<Album> getAllAlbums() {
        return repo.getAllAlbums();
    }

    public Album getAlbumByTitle(String title) {
        return repo.getAlbumByTitle(title);
    }

    public Album getAlbumWithArtistName(String title) {
        return repo.getAlbumWithArtistName(title);
    }
}
