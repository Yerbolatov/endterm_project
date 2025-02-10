package models;

import java.util.List;

public class Collection {
    private int id;
    private int userId;
    private String name;
    private List<Album> albums;
    private List<Artist> artists;

    public Collection(int id, int userId, String name, List<Album> albums, List<Artist> artists) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.albums = albums;
        this.artists = artists;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<Album> getAlbums() { return albums; }
    public void setAlbums(List<Album> albums) { this.albums = albums; }

    public List<Artist> getArtists() { return artists; }
    public void setArtists(List<Artist> artists) { this.artists = artists; }

    @Override
    public String toString() {
        return "Collection{id=" + id + ", userId=" + userId + ", name='" + name + "', albums=" + albums + ", artists=" + artists + "}";
    }
}
