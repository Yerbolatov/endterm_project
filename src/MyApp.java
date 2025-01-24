import repositories.UserLogin;
import repositories.UserRegister;
import repositories.interfaces.iUserLogin;
import repositories.interfaces.iUserRegister;

import java.sql.SQLException;
import java.util.Scanner;

public class MyApp {
    private iUserRegister userRegister;
    private iUserLogin userLogin;
    Scanner scanner = new Scanner(System.in);

    public MyApp(iUserRegister userRegister, iUserLogin userLogin) {
        this.userRegister = userRegister;
        this.userLogin = userLogin;
    }

    private void mainmenu() {
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. Exit");
    }

    public void start() {
        while (true) {
            mainmenu();

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter username: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();

                    try {
                        userRegister.registerUser(username, password);
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                    }
                    return;
                case 2:
                    System.out.print("Enter username: ");
                    username = scanner.nextLine();
                    System.out.print("Enter password: ");
                    password = scanner.nextLine();

                    try {
                        boolean isLoggedIn = userLogin.loginUser(username, password);
                        if (isLoggedIn) {
                            System.out.println("Welcome, " + username + ".");
                        }
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                    }
                case 3:
                    System.out.println("Exiting.");
                    return;
                default:
                    return;
            }
        }
    }
}
