package repositories.interfaces;

import models.Artist;
import java.util.List;

public interface iArtistRepository {
    boolean createArtist(Artist artist);
    Artist getArtist(int id);
    List<Artist> getAllArtists();

    Artist getArtistByName(String name);
}
