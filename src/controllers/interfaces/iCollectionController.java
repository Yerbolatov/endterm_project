package controllers.interfaces;

import models.Collection;

public interface iCollectionController {
    String createCollection(int userId, String name);
    String addAlbumToCollection(int collectionId, int albumId);
    String addArtistToCollection(int collectionId, int artistId);
    String removeAlbumFromCollection(int collectionId, int albumId);
    String removeArtistFromCollection(int collectionId, int artistId);
    Collection getCollection(int collectionId);
}
