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
