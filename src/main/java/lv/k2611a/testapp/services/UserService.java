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

    @PostConstruct
    public void init() {
        users = new HashMap<Long, User>();
        put(new User(1, "Kirill","passKirill"));
        put(new User(2, "Katja","passKatja"));
        put(new User(3, "Anzella","passAnzella"));
        put(new User(4, "Kolja","passKolja"));
    }

    private void put(User u) {
        if (users.containsKey(u.getId())) {
            throw new IllegalArgumentException("User with id " + u.getId() + " already exists");
        }
        users.put(u.getId(), u);
    }

    public Map<Long, User> getAll() {
        return users;
    }

    public User getUserById(long id) {
        long userId = id;
        if (users.containsKey(userId)) {
            User user = users.get(userId);
            return user;

        }
        return null;
    }

    public User getUserByName(String userName) {
        if (userName != null) {
              for (Map.Entry entry : users.entrySet()) {
                  User value = (User) entry.getValue();
                        if ((value.getName().equals(userName))) {
                            return value;
                         }
                }
        }
        return null;
    }

    public Boolean authenticateUser(String userName, String password) {
        if (userName != null) {
                for (Map.Entry entry : users.entrySet()) {
                    User value = (User) entry.getValue();
                    if ((value.getName().equals(userName))&&(value.getPassword().equals(password))) {
                        return true;
                    }
                }

            }
        return false;

    }
}