import controllers.interfaces.iUserController;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UserApp {
    private final iUserController userController;
    private final Scanner scanner = new Scanner(System.in);

    public UserApp(iUserController userController) {
        this.userController = userController;
    }

    private void mainmenu() {
        System.out.println("1. Create new user");
        System.out.println("2. Get user by id");
        System.out.println("3. Exit");
    }

    public void start() {
        while (true) {
            mainmenu();
            try {
                int option = scanner.nextInt();

                switch (option) {
                    case 1: createUser(); break;
                    case 2: getUser(); break;
                    default: return;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input");
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
    public void createUser() {
        System.out.print("Enter username: ");
        String username = scanner.next();

        String response = userController.createUser(username);
        System.out.println(response);
    }
    public void getUser() {
        System.out.print("Enter id: ");

        int id = scanner.nextInt();

        String response = userController.getUser(id);
        System.out.println(response);
    }

}
// this is redundant by now