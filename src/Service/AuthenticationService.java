package Service;

import DB.User;
import DB.UserDao;
import DB.User;
import DB.UserDao;

import java.sql.SQLException;

public class AuthenticationService {
    private UserDao userDao;

    public AuthenticationService() {
        userDao = new UserDao();
    }

    public int register(User user) throws SQLException {
        int result = userDao.create(user);
        return result;
    }
    public User GetByEmail(String email) throws SQLException {
        return userDao.get().stream().filter(x -> x.getEmail().equals(email)).findFirst().orElse(null);
    }
    public User logIn(String email,String password) throws SQLException {
        User user = GetByEmail(email);
        if (user != null) {
            if (user.getPasswordEncrypted().equals(password)) {
                return user;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
}
