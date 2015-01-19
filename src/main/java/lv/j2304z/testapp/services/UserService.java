package lv.j2304z.testapp.services;


import lv.j2304z.testapp.services.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lv.j2304z.testapp.domain.User;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@Service
public class UserService {

    @Autowired
    BlogService blogService;
    @Autowired
    private PasswordCheckService passwordCheckServise;

    private Map<Long, User> userById;
    private Map<String, User> userByName;
    private long currentUserId = 0L;

    @PostConstruct
    public void init() throws UserAlreadyExistException, DublicatedSymbolException, SmallPasswodsException {
        userById = new HashMap<Long, User>();
        userByName = new HashMap<String, User>();

        registerUser("Kirill", "passKirill");
        registerUser("Katja", "passKatja");
        registerUser("Anzella", "passAnzella");
        registerUser("Kolja", "passKolja");
    }


    private void put(User u) {

        if (userById.containsKey(u.getId())) {
            throw new IllegalArgumentException("User with id " + u.getId() + " already exists");
        }
        if (userByName.containsKey(u.getName())) {
            throw new IllegalArgumentException("User with name " + u.getName() + " already exists");
        }
        userById.put(u.getId(), u);
        userByName.put(u.getName(), u);
    }

    public Map<Long, User> getAll() {
        return userById;
    }

    public User getUserById(Long userId) {
        return userById.get(userId);
    }

    public User getUserByName(String userName) {

            return userByName.get(userName);
        }

    public void authenticateUser(String userName, String password) throws IncorrectPasswordException,UserNotFoundException{
        User user = getUserByName(userName);
        if (user == null) {
            throw new UserNotFoundException();
            }
        if (user.getPassword().equals(password)){
            return;
        }
        else{
            throw new IncorrectPasswordException();
        }
    }

    public void deleteUser(long userId) throws IOException {
        User user = getUserById(userId);
        userById.remove(userId);
        userByName.remove(user.getName());

         blogService.deleteUserBlogs(userId);

    }
    public void registerUser(String userName, String password) throws UserAlreadyExistException, DublicatedSymbolException, SmallPasswodsException {

        if (userByName.containsKey(userName)) {
            throw new UserAlreadyExistException();
        }
        passwordCheckServise.check(password);
        long id= currentUserId++;
        User user = new User(id,userName,password);
        userById.put(id, user);
        userByName.put(userName, user);

    }

}