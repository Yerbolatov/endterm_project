package controllers;

import controllers.interfaces.iArtistController;
import models.Artist;
import repositories.interfaces.iArtistRepository;
import java.util.List;

public class ArtistController implements iArtistController {
    private final iArtistRepository repo;

    public ArtistController(iArtistRepository repo) {
        this.repo = repo;
    }

    public String createArtist(String name, String country) {
        Artist artist = new Artist(0, name, country);
        boolean created = repo.createArtist(artist);
        return created ? "Artist created" : "Artist was not created";
    }

    public String getArtist(int id) {
        Artist artist = repo.getArtist(id);
        return artist == null ? "Artist was not found" : artist.toString();
    }

    public int getArtistIdByName(String artistName) {
        Artist artist = repo.getArtistByName(artistName);
        if (artist != null) {
            return artist.getId();
        }
        return -1;
    }

    public List<Artist> getAllArtists() {
        return repo.getAllArtists();
    }
}
