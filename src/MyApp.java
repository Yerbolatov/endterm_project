import controllers.UserListController;
import models.Album;
import models.Artist;
import models.UserList;
import repositories.AlbumRepository;
import repositories.ArtistRepository;
import repositories.UserListRepository;
import service.interfaces.iUserLogin;
import service.interfaces.iUserRegister;
import controllers.ArtistController;
import controllers.AlbumController;
import data.interfaces.IDB;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class MyApp {
    private iUserRegister userRegister;
    private iUserLogin userLogin;
    private ArtistController artistController;
    private AlbumController albumController;
    private UserListController userListController;
    private boolean isLoggedIn = false;
    private String loggedInUser = "";
    Scanner scanner = new Scanner(System.in);

    public MyApp(iUserRegister userRegister, iUserLogin userLogin, IDB db) {
        this.userRegister = userRegister;
        this.userLogin = userLogin;

        this.artistController = new ArtistController(new ArtistRepository(db));
        this.albumController = new AlbumController(new AlbumRepository(db));
        this.userListController = new UserListController(new UserListRepository(db));
    }

    private void mainmenu() {
        if (!isLoggedIn) {
            System.out.println("1. Register");
            System.out.println("2. Login");
        } else {
            System.out.println("3. Add Artist");
            System.out.println("4. Add Album");
            System.out.println("5. Create List");
            System.out.println("6. Add Album to List");
            System.out.println("7. See my lists");
            System.out.println("8. See artists/albums");
        }
        System.out.println("0. Exit");
    }

    public void start() {
        while (true) {
            mainmenu();

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    if (!isLoggedIn) {
                        System.out.print("Enter username: ");
                        String username = scanner.nextLine();
                        System.out.print("Enter password: ");
                        String password = scanner.nextLine();

                        try {
                            userRegister.registerUser(username, password);
                            System.out.println("Registration successful. You can now log in.");
                        } catch (SQLException e) {
                            System.out.println(e.getMessage());
                        }
                    } else {
                        System.out.println("You are already logged in.");
                    }
                    break;
                case 2:
                    if (!isLoggedIn) {
                        System.out.print("Enter username: ");
                        String username = scanner.nextLine();
                        System.out.print("Enter password: ");
                        String password = scanner.nextLine();

                        try {
                            boolean isLoggedIn = userLogin.loginUser(username, password);
                            if (isLoggedIn) {
                                this.isLoggedIn = true;
                                this.loggedInUser = username;
                                System.out.println("Welcome, " + username + ".");
                            } else {
                                System.out.println("Invalid credentials.");
                            }
                        } catch (SQLException e) {
                            System.out.println(e.getMessage());
                        }
                    } else {
                        System.out.println("You are already logged in.");
                    }
                    break;
                case 3:
                    if (isLoggedIn) {
                        System.out.print("Enter artist name: ");
                        String artistName = scanner.nextLine();
                        System.out.print("Enter artist country: ");
                        String artistCountry = scanner.nextLine();

                        String artistResponse = artistController.createArtist(artistName, artistCountry);
                        System.out.println(artistResponse);
                    } else {
                        System.out.println("You must be logged in to add an artist.");
                    }
                    break;
                case 4:
                    if (isLoggedIn) {
                        System.out.print("Enter artist name: ");
                        String artistName = scanner.nextLine();
                        int artistId = artistController.getArtistIdByName(artistName);
                        if (artistId < 1) {
                            System.out.println("Artist not found.");
                            break;
                        }
                        System.out.print("Enter album title: ");
                        String albumTitle = scanner.nextLine();
                        System.out.print("Enter album release date (yyyy-mm-dd): ");
                        String releaseDate = scanner.nextLine();
                        System.out.print("Enter album genre: ");
                        String genre = scanner.nextLine();

                        String albumResponse = albumController.createAlbum(artistId, albumTitle, releaseDate, genre);
                        System.out.println(albumResponse);
                    } else {
                        System.out.println("You must be logged in to add an album.");
                    }
                    break;
                case 5:
                    if (isLoggedIn) {
                        System.out.print("Enter list title: ");
                        String title = scanner.nextLine();
                        System.out.println(userListController.createList(title, loggedInUser));
                        break;
                    }
                case 6:
                    if (isLoggedIn) {
                        System.out.print("Enter list title: ");
                        String listTitle = scanner.nextLine();
                        System.out.print("Enter album title: ");
                        String albumTitle = scanner.nextLine();
                        System.out.print("Enter comment: ");
                        String comment = scanner.nextLine();

                        System.out.println(userListController.addAlbumToList(listTitle, albumTitle, comment, loggedInUser));
                        break;
                    }
                case 7:
                    System.out.println("Your lists:");
                    List<UserList> userLists = userListController.getUserLists(loggedInUser);
                    if (userLists.isEmpty()) {
                        System.out.println("You have no lists.");
                    } else {
                        userLists.forEach(userList -> {
                            System.out.println(userList);
                        });
                    }
                    break;
                case 8:
                    seeArtistsAlbums();
                    break;
                case 0:
                    System.out.println("Exiting.");
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
    private void seeArtistsAlbums() {
        while (true) {
            System.out.println("1. See Artists");
            System.out.println("2. See Albums");
            System.out.println("0. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    List<Artist> artists = artistController.getAllArtists();
                    if (artists.isEmpty()) {
                        System.out.println("No artists found.");
                    } else {
                        System.out.println("Artists:");
                        artists.forEach(artist -> System.out.println(artist.getName()));
                    }
                    break;
                case 2:
                    List<Album> albums = albumController.getAllAlbums();
                    if (albums.isEmpty()) {
                        System.out.println("No albums found.");
                    } else {
                        System.out.println("Albums:");
                        albums.forEach(album -> System.out.println(album.getTitle()));
                    }
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid option.");
            }

            System.out.print("Enter an artist/album name to see full details, or 0 to exit: ");
            String input = scanner.nextLine();

            if (input.equals("0")) {
                return;
            } else {
                showDetails(input);
            }
        }
    }

    private void showDetails(String name) {
        Artist artist = artistController.getArtistByName(name);
        if (artist != null) {
            System.out.println("Artist Details:");
            System.out.println("Name: " + artist.getName());
            System.out.println("Country: " + artist.getCountry());
            return;
        }

        Album album = albumController.getAlbumByTitle(name);
        if (album != null) {
            System.out.println("Album Details:");
            System.out.println("Title: " + album.getTitle());
            System.out.println("Genre: " + album.getGenre());
            System.out.println("Release Date: " + album.getReleaseDate());
            String artistName = albumController.getArtistNameForAlbum(album.getArtistId());
            System.out.println("Artist: " + artistName);
            return;
        }

        System.out.println("No details found for: " + name);
    }
}

