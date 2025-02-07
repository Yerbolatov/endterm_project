package controllers.interfaces;


import models.Artist;

public interface iArtistController {
    String createArtist(String name, String country);
    String getArtist(int id);

    int getArtistIdByName(String artistName);

    Artist getArtistByName(String name);
}