package controllers;

import controllers.interfaces.iUserListController;
import models.UserList;
import repositories.interfaces.iUserListRepository;
import java.util.List;

public class UserListController implements iUserListController {
    private final iUserListRepository repo;

    public UserListController(iUserListRepository repo) {
        this.repo = repo;
    }

    public String createList(String title, String createdBy) {
        boolean created = repo.createList(title, createdBy);
        return created ? "List created successfully." : "Failed to create list.";
    }

    public String addAlbumToList(String listTitle, String albumTitle, String comment, String username) {
        int listId = repo.getListIdByTitle(listTitle);
        int albumId = repo.getAlbumIdByTitle(albumTitle);

        if (listId == -1) {
            return "Error: List not found!";
        }
        if (albumId == -1) {
            return "Error: Album not found!";
        }

        boolean added = repo.addAlbumToList(listId, albumId, comment, username);
        return added ? "Album added to list successfully!" : "Failed to add album to list.";
    }

    public List<UserList> getUserLists(String username) {
        return repo.getListsByUser(username);
    }
}
