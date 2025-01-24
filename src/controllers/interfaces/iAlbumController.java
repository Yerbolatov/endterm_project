package controllers.interfaces;

public interface iAlbumController {
    String createAlbum(int artistId, String title, String releaseDate, String genre, double rating);
    String getAlbum(int id);
}