import data.PostgreDB;
import data.interfaces.IDB;
import service.UserLogin;
import service.UserRegister;
import service.interfaces.iUserLogin;
import service.interfaces.iUserRegister;


public class Main {
    public static void main(String[] args) {
        IDB db = PostgreDB.getInstance("jdbc:postgresql://localhost:5432", "postgres", "0000", "endterm");

        iUserRegister register = new UserRegister(db);
        iUserLogin login = new UserLogin(db);
        MyApp app = new MyApp(register, login, db);

        app.start();


        db.close();
    }
}