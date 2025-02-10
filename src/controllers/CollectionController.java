package controllers;

import controllers.interfaces.iCollectionController;
import models.Collection;
import repositories.interfaces.iCollectionRepository;

public class CollectionController implements iCollectionController {
    private final iCollectionRepository repo;

    public CollectionController(iCollectionRepository repo) {
        this.repo = repo;
    }

    public String createCollection(int userId, String name) {
        if (userId <= 0) return "Invalid user ID";
        if (name == null || name.trim().isEmpty()) return "Collection name cannot be empty";

        boolean created = repo.createCollection(userId, name);
        return created ? "Collection created" : "Collection was not created";
    }

    public String addAlbumToCollection(int collectionId, int albumId) {
        if (collectionId <= 0 || albumId <= 0) return "Invalid collection or album ID";

        boolean added = repo.addAlbumToCollection(collectionId, albumId);
        return added ? "Album added to collection" : "Failed to add album";
    }

    public String addArtistToCollection(int collectionId, int artistId) {
        if (collectionId <= 0 || artistId <= 0) return "Invalid collection or artist ID";

        boolean added = repo.addArtistToCollection(collectionId, artistId);
        return added ? "Artist added to collection" : "Failed to add artist";
    }

    public String removeAlbumFromCollection(int collectionId, int albumId) {
        if (collectionId <= 0 || albumId <= 0) return "Invalid collection or album ID";

        boolean removed = repo.removeAlbumFromCollection(collectionId, albumId);
        return removed ? "Album removed from collection" : "Failed to remove album";
    }

    public String removeArtistFromCollection(int collectionId, int artistId) {
        if (collectionId <= 0 || artistId <= 0) return "Invalid collection or artist ID";

        boolean removed = repo.removeArtistFromCollection(collectionId, artistId);
        return removed ? "Artist removed from collection" : "Failed to remove artist";
    }

    public Collection getCollection(int collectionId) {
        if (collectionId <= 0) return null;

        return repo.getCollection(collectionId);
    }
}
