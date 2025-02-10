package controllers;

import controllers.interfaces.iAlbumController;

import models.Album;
import repositories.interfaces.iAlbumRepository;
import java.util.List;

public class AlbumController implements iAlbumController {
    private final iAlbumRepository repo;

    public AlbumController(iAlbumRepository repo) {
        this.repo = repo;
    }

    public String createAlbum(int artistId, String title, String releaseDate, String genre, double rating) {
        if (artistId <= 0) return "Invalid artist ID";
        if (title == null || title.trim().isEmpty()) return "Title cannot be empty";
        if (releaseDate == null || releaseDate.trim().isEmpty()) return "Release date cannot be empty";
        if (genre == null || genre.trim().isEmpty()) return "Genre cannot be empty";
        if (rating < 0 || rating > 10) return "Rating must be between 0 and 10";

        Album album = new Album(0, artistId, title, releaseDate, genre, rating);
        boolean created = repo.createAlbum(album);
        return created ? "Album created" : "Album was not created";
    }


    public String getAlbum(int id) {
        Album album = repo.getAlbum(id);
        return album == null ? "Album was not found" : album.toString();
    }

    public List<Album> getAllAlbums() {
        return repo.getAllAlbums();
    }
}
