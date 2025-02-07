package controllers.interfaces;

import models.UserList;
import java.util.List;

public interface iUserListController {
    String createList(String title, String createdBy);
    String addAlbumToList(String listTitle, String albumTitle, String comment, String username);
    List<UserList> getUserLists(String username);
}
