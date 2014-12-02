package lv.k2611a.testapp.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.k2611a.testapp.domain.User;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;


@Service
public class UserService {

    @Autowired
    BlogService blogService;
    private Map<Long, User> users;
    private Map<String, Long> userNameMap;
    private Long userId = 0L;

    @PostConstruct
    public void init() {
        users = new HashMap<Long, User>();
        userNameMap = new HashMap<String, Long>();

        registerUser( "Kirill", "passKirill");
        registerUser("Katja", "passKatja");
        registerUser("Anzella", "passAnzella");
        registerUser("Kolja", "passKolja");
    }


    private void put(User u) {

        if (users.containsKey(u.getId())) {
            throw new IllegalArgumentException("User with id " + u.getId() + " already exists");
        }
        if (userNameMap.containsKey(u.getName())) {
            throw new IllegalArgumentException("User with name " + u.getName() + " already exists");
        }
        users.put(u.getId(), u);
        userNameMap.put(u.getName(), u.getId());
    }

    public Map<Long, User> getAll() {
        return users;
    }

    public User getUserById(Long id) {
        Long userId = id;
        if (users.containsKey(userId)) {
            User user = users.get(userId);
            return user;

        }
        return null;
    }

    public Long getUserByName(String name) {
        String userName = name;
        if (userNameMap.containsKey(userName)) {
            Long userId = userNameMap.get(userName);
            return userId;
        }
        return null;
    }

    public boolean authenticateUser(String userName, String password) {
        User user = getUserById(getUserByName(userName));
        if (user != null) {
            if (user.getPassword().equals(password)) {
                return true;
            }
        } else {
            throw new IllegalArgumentException("User doesn't exists");
        }
        return false;

    }

    public void deleteUser(Long userId) {
        if (userId == null) {
            throw new IllegalArgumentException("Empty user");
        }
        User user = getUserById(userId);
        users.remove(userId);
        userNameMap.remove(user);

        try {

            blogService.deleteUserBlogs(userId);
        } catch (IllegalArgumentException e) {

        }
        ;
    }
    public void registerUser(String userName, String password){
        Long id=userId++;
        if (userNameMap.containsKey(userName)) {
            throw new IllegalArgumentException("User with name " + userName + " already exists");
        }
        User user = new User(id,userName,password);
        users.put(id,user);
        userNameMap.put(userName,id);

    }
}