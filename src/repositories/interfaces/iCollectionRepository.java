package repositories.interfaces;

import models.Album;
import java.util.List;
import java.util.Optional;

public interface iCollectionRepository {
    boolean addAlbumToCollection(int userId, Album album);
    boolean removeAlbumFromCollection(int userId, int albumId);
    List<Album> getUserCollection(int userId);
    Optional<Album> findAlbumInCollection(int userId, String title);
}