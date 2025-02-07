package models;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UserList {
    private int id;
    private String title;
    private String createdBy;
    private List<Album> albums;
    private Map<Integer, String> albumComments;

    public UserList(int id, String title, String createdBy, List<Album> albums, Map<Integer, String> albumComments) {
        this.id = id;
        this.title = title;
        this.createdBy = createdBy;
        this.albums = albums;
        this.albumComments = albumComments;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getCreatedBy() { return createdBy; }
    public void setCreatedBy(String createdBy) { this.createdBy = createdBy; }

    public List<Album> getAlbums() { return albums; }
    public void setAlbums(List<Album> albums) { this.albums = albums; }

    public Map<Integer, String> getAlbumComments() { return albumComments; }
    public void setAlbumComments(Map<Integer, String> albumComments) { this.albumComments = albumComments; }

    public List<Album> filterAlbumsByGenre(String genre) {
        return albums.stream()
                .filter(album -> album.getGenre().equalsIgnoreCase(genre))
                .collect(Collectors.toList());
    }



    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("List Title: ").append(title).append("\n");
        sb.append("Created By: ").append(createdBy).append("\n");

        if (albums.isEmpty()) {
            sb.append("No albums in this list.");
        } else {
            sb.append("Albums in this list:\n");
            for (Album album : albums) {
                sb.append(" - ").append(album.getTitle()).append(" by ").append(album.getArtistId()).append("\n");
                if (albumComments.containsKey(album)) {
                    sb.append("   Comment: ").append(albumComments.get(album)).append("\n");
                }
            }
        }
        return sb.toString();
    }
}

