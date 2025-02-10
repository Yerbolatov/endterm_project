package models;

public class Review {
    private int id;
    private int userId;
    private int albumId;
    private String comment;
    private double rating;

    public Review(int id, int userId, int albumId, String comment, double rating) {
        this.id = id;
        this.userId = userId;
        this.albumId = albumId;
        this.comment = comment;
        this.rating = rating;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public int getAlbumId() { return albumId; }
    public void setAlbumId(int albumId) { this.albumId = albumId; }

    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }

    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }

    @Override
    public String toString() {
        return "Review{id=" + id + ", userId=" + userId + ", albumId=" + albumId +
                ", rating=" + rating + ", comment='" + comment + "'}";
    }
}
