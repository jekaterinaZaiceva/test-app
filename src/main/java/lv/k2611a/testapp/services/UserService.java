package lv.k2611a.testapp.services;


import org.springframework.stereotype.Service;

import lv.k2611a.testapp.domain.User;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;


@Service
public class UserService {

    private Map<Long,User> users;

    @PostConstruct
    public void init() {
        users = new HashMap<Long, User>();
        put(new User(1, "Kirill"));
        put(new User(2,"Katja"));
        put(new User(3,"Anzella"));
        put(new User(4,"Kolja"));
    }

    private void put(User u) {
        if (users.containsKey(u.getId())) {
            throw new IllegalArgumentException("User with id " + u.getId() + " already exists");
        }
        users.put(u.getId(), u);
    }

    public Map<Long,User> getAll(){
        return users;
    }

    public User getUserById(long id){
        long userId = id;
        if(users.containsKey(userId)){
            User user = users.get(userId);
            return user;

        }
        return null;
    }
}