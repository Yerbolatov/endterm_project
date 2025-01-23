import controllers.UserController;
import controllers.interfaces.iUserController;
import data.PostgreDB;
import data.interfaces.IDB;
import repositories.UserRepository;
import repositories.interfaces.iUserRepository;

public class Main {
    public static void main(String[] args) {
        IDB db = new PostgreDB("jdbc:postgresql://localhost:5432", "postgres", "0000", "endterm");



        db.close();
    }
}