package controllers;

import controllers.interfaces.iCollectionController;
import models.Album;
import repositories.interfaces.iCollectionRepository;
import java.util.List;
import java.util.Optional;

public class CollectionController implements iCollectionController {
    private final iCollectionRepository repo;

    public CollectionController(iCollectionRepository repo) {
        this.repo = repo;
    }

    @Override
    public String addAlbumToCollection(int userId, Album album) {
        boolean added = repo.addAlbumToCollection(userId, album);
        return added ? "Album added to collection" : "Failed to add album";
    }

    @Override
    public String removeAlbumFromCollection(int userId, int albumId) {
        boolean removed = repo.removeAlbumFromCollection(userId, albumId);
        return removed ? "Album removed from collection" : "Album not found in collection";
    }

    @Override
    public List<Album> getUserCollection(int userId) {
        return repo.getUserCollection(userId);
    }

    @Override
    public String findAlbumInCollection(int userId, String title) {
        Optional<Album> album = repo.findAlbumInCollection(userId, title);
        return album.map(Album::toString).orElse("Album not found in collection");
    }
}
