package repositories.interfaces;

import models.UserList;
import java.util.List;

public interface iUserListRepository {
    boolean createList(String title, String createdBy);
    int getListIdByTitle(String title);
    Integer getAlbumIdByTitle(String title);
    boolean addAlbumToList(int listId, int albumId, String comment, String username);
    UserList getListById(int id);
    List<UserList> getListsByUser(String username);
}