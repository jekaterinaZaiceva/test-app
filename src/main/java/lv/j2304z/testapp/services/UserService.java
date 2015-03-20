package lv.j2304z.testapp.services;


import lv.j2304z.testapp.dao.UserDao;
import lv.j2304z.testapp.services.exceptions.*;
import lv.j2304z.testapp.sessions.CurrentUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lv.j2304z.testapp.domain.User;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class UserService {

    @Autowired
    BlogService blogService;
    @Autowired
    private PasswordCheckService passwordCheckServise;
    @Autowired
    private UserDao userDao;
    @Autowired
    private CurrentUser currentUser;

    private long currentUserId = 0L;
    private static final Logger log = LoggerFactory.getLogger(UserService.class);

//    @PostConstruct
//    public void init() throws UserAlreadyExistException, DublicatedSymbolException, SmallPasswodsException {
//
//        registerUser("Kirill", "passKirill");
//        registerUser("Katja", "passKatja");
//        registerUser("Anzella", "passAnzella");
//        registerUser("Kolja", "passKolja");
//    }
        @PostConstruct
        public void init() {
            }


    private synchronized void put(String name, String password) {

//
//        if (userById.containsKey(u.getId())) {
//            throw new IllegalArgumentException("User with id " + u.getId() + " already exists");
//        }
//        if (userByName.containsKey(u.getName())) {
//            throw new IllegalArgumentException("User with name " + u.getName() + " already exists");
//        }
        //userById.put(u.getId(), u);
        //userByName.put(u.getName(), u);
            User user = new User(name, password);
            userDao.addUser(user);

    }


    public synchronized User getUserById(long userId) {
        return userDao.getUserById(userId);
    }

    public synchronized  User getUserByName(String userName) {

        return userDao.getUserByName(userName);
        }

    public synchronized void authenticateUser(String userName, String password) throws IncorrectPasswordException,UserNotFoundException{
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

    public synchronized void deleteUser(long userId) throws IOException {
        long currentUserId = currentUser.getId();
        if (currentUserId ==userId) {
            userDao.deleteUser(userId);
            blogService.deleteUserBlogs(userId);
        }
        else {
            log.error("Ошибка при удалении пользователя");
        }
        //userById.remove(userId);
        //userByName.remove(user.getName());


    }
    public synchronized void registerUser(String userName, String password) throws UserAlreadyExistException, DublicatedSymbolException, SmallPasswodsException {
        User userByName = getUserByName(userName);
//        if (userByName.containsKey(userName)) {
//            throw new UserAlreadyExistException();
//        }
        passwordCheckServise.check(password);
        long id= currentUserId++;
        User user = new User(userName,password);
        userDao.addUser(user);
//        userById.put(id, user);
//        userByName.put(userName, user);

    }

    public synchronized List<User> getAllUsers(){
        return new ArrayList<User>(userDao.getAllUsers());
    }
}