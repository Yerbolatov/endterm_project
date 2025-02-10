import repositories.interfaces.iUserRepository;
import models.User;

public class UserApp {
    private final iUserRepository userRepository;

    public UserApp(iUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean registerUser(String username) {
        User user = new User(0, username);
        return userRepository.createUser(user);
    }

    public User loginUser(int id) {
        return userRepository.getUser(id);
    }
}
