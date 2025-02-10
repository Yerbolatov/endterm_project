import data.PostgreDB;
import data.interfaces.IDB;
import repositories.UserRepository;
import repositories.interfaces.iUserRepository;

public class Main {
    public static void main(String[] args) {
        IDB db = new PostgreDB("jdbc:postgresql://localhost:5432/endterm", "postgres", "0000");

        iUserRepository userRepository = new UserRepository(db);

        MyApp app = new MyApp(userRepository);
        app.start();

        db.close();
    }
}
