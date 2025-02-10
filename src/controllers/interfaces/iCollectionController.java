package controllers.interfaces;

import models.Album;
import java.util.List;

public interface iCollectionController {
    String addAlbumToCollection(int userId, Album album);
    String removeAlbumFromCollection(int userId, int albumId);
    List<Album> getUserCollection(int userId);
    String findAlbumInCollection(int userId, String title);
}
