package repositories.interfaces;

import models.Album;
import java.util.List;

public interface iAlbumRepository {
    boolean createAlbum(Album album);
    Album getAlbum(int id);
    List<Album> getAllAlbums();
    int getAlbumIdByTitle(String title);

    Album getAlbumByTitle(String title);

    String getArtistNameById(int artistId);
}
