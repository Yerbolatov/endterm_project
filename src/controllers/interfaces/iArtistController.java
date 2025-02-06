package controllers.interfaces;


public interface iArtistController {
    String createArtist(String name, String country);
    String getArtist(int id);

    int getArtistIdByName(String artistName);
}