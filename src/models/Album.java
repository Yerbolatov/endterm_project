package models;

public class Album {
    private int id;
    private int artistId;
    private String title;
    private String releaseDate;
    private String genre;
    private double rating;

    public Album() {}

    public Album(int id, int artistId, String title, String releaseDate, String genre, double rating) {
        this.id = id;
        this.artistId = artistId;
        this.title = title;
        this.releaseDate = releaseDate;
        this.genre = genre;
        this.rating = rating;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getArtistId() { return artistId; }
    public void setArtistId(int artistId) { this.artistId = artistId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getReleaseDate() { return releaseDate; }
    public void setReleaseDate(String releaseDate) { this.releaseDate = releaseDate; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }

    @Override
    public String toString() {
        return "Album{id=" + id + ", artistId=" + artistId + ", title='" + title + "', releaseDate='" + releaseDate +
                "', genre='" + genre + "', rating=" + rating + "}";
    }
}
