package controllers.interfaces;

import models.Album;

public interface iAlbumController {
    String createAlbum(int artistId, String title, String releaseDate, String genre);
    String getAlbum(int id);
    String getAlbumArtist(int id);

    Album getAlbumByTitle(String title);
}