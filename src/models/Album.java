package models;

import java.sql.Date;

public class Album {
    private int id;
    private int artistId;
    private String title;
    private Date releaseDate;
    private String genre;

    public Album() {}

    public Album(int id, int artistId, String title, Date releaseDate, String genre) {
        this.id = id;
        this.artistId = artistId;
        this.title = title;
        this.releaseDate = releaseDate;
        this.genre = genre;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getArtistId() { return artistId; }
    public void setArtistId(int artistId) { this.artistId = artistId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public Date getReleaseDate() { return releaseDate; }
    public void setReleaseDate(Date releaseDate) { this.releaseDate = releaseDate; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    @Override
    public String toString() {
        return "Album{id=" + id + ", artistId=" + artistId + ", title='" + title + "', releaseDate='" + releaseDate +
                "', genre='" + genre + "}";
    }
}
