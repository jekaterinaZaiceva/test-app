package lv.k2611a.testapp.services;


import lv.k2611a.testapp.domain.Blog;
import org.springframework.stereotype.Service;

import lv.k2611a.testapp.domain.User;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class UserService {

    private Map<Long, User> users;
    private Map<String, Long> userNameMap;

    @PostConstruct
    public void init() {
        users = new HashMap<Long, User>();
        userNameMap = new HashMap<String, Long>();
        put(new User(1, "Kirill","passKirill"));
        put(new User(2, "Katja","passKatja"));
        put(new User(3, "Anzella","passAnzella"));
        put(new User(4, "Kolja","passKolja"));
    }


    private void put(User u) {
        if (users.containsKey(u.getId())) {
            throw new IllegalArgumentException("User with id " + u.getId() + " already exists");
        }
        if(userNameMap.containsKey(u.getName())){
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
        if (user!=null){
            if (user.getPassword().equals(password)){
                return true;
            }
        }
        else {
            throw new IllegalArgumentException("User doesn't exists");
        }
        return false;

    }
}