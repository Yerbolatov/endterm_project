import data.PostgreDB;
import data.interfaces.IDB;
import repositories.UserLogin;
import repositories.UserRegister;
import repositories.interfaces.iUserLogin;
import repositories.interfaces.iUserRegister;


public class Main {
    public static void main(String[] args) {
        IDB db = new PostgreDB("jdbc:postgresql://localhost:5432", "postgres", "0000", "endterm");

        iUserRegister register = new UserRegister(db);
        iUserLogin login = new UserLogin(db);
        MyApp app = new MyApp(register, login);

        app.start();


        db.close();
    }
}