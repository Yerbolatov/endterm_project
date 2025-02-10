import repositories.interfaces.iUserRepository;
import models.User;
import java.util.Scanner;

public class MyApp {
    private final iUserRepository userRepository;
    private final Scanner scanner = new Scanner(System.in);
    private boolean loggedIn = false;
    private User currentUser;

    public MyApp(iUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void start() {
        while (true) {
            if (!loggedIn) {
                showAuthMenu();
            } else {
                showUserMenu();
            }
        }
    }

    private void showAuthMenu() {
        System.out.println("\n🎵 MUSIC LIBRARY 🎵");
        System.out.println("1. Register");
        System.out.println("2. Login (by ID)");
        System.out.println("3. Exit");
        System.out.print("Choose an option: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1 -> registerUser();
            case 2 -> loginUser();
            case 3 -> exitApp();
            default -> System.out.println("Invalid choice. Try again.");
        }
    }

    private void registerUser() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        User user = new User(0, username);
        if (userRepository.createUser(user)) {
            System.out.println("User registered successfully!");
        } else {
            System.out.println("Registration failed.");
        }
    }

    private void loginUser() {
        System.out.print("Enter user ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        User user = userRepository.getUser(id);
        if (user != null) {
            loggedIn = true;
            currentUser = user;
            System.out.println("Welcome, " + user.getUsername() + "!");
        } else {
            System.out.println("User not found.");
        }
    }

    private void showUserMenu() {
        System.out.println("\n🎵 Welcome, " + currentUser.getUsername() + " 🎵");
        System.out.println("1. View Collections");
        System.out.println("2. Create Collection");
        System.out.println("3. Logout");
        System.out.print("Choose an option: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1 -> System.out.println("Viewing collections...");
            case 2 -> System.out.println("Creating a collection...");
            case 3 -> logout();
            default -> System.out.println("Invalid choice.");
        }
    }

    private void logout() {
        loggedIn = false;
        currentUser = null;
        System.out.println("Logged out successfully.");
    }

    private void exitApp() {
        System.out.println("Goodbye!");
        System.exit(0);
    }
}
