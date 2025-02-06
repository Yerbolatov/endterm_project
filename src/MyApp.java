import repositories.AlbumRepository;
import repositories.ArtistRepository;
import service.interfaces.iUserLogin;
import service.interfaces.iUserRegister;
import controllers.ArtistController;
import controllers.AlbumController;
import data.interfaces.IDB;

import java.sql.SQLException;
import java.util.Scanner;

public class MyApp {
    private iUserRegister userRegister;
    private iUserLogin userLogin;
    private ArtistController artistController;
    private AlbumController albumController;
    private boolean isLoggedIn = false;
    Scanner scanner = new Scanner(System.in);

    public MyApp(iUserRegister userRegister, iUserLogin userLogin, IDB db) {
        this.userRegister = userRegister;
        this.userLogin = userLogin;

        this.artistController = new ArtistController(new ArtistRepository(db));
        this.albumController = new AlbumController(new AlbumRepository(db));
    }

    private void mainmenu() {
        if (!isLoggedIn) {
            System.out.println("1. Register");
            System.out.println("2. Login");
        } else {
            System.out.println("3. Add Artist");
            System.out.println("4. Add Album");
        }
        System.out.println("5. Exit");
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
                    System.out.println("Exiting.");
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}

