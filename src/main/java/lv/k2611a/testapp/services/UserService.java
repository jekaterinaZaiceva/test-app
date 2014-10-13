package lv.k2611a.testapp.services;


import org.springframework.stereotype.Service;

import lv.k2611a.testapp.domain.User;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;


@Service
public class UserService {

    private Map<Integer,User> users;

    @PostConstruct
    public void init() {
        users = new HashMap<Integer, User>();
        users.put(1, new User("Kirill"));
        users.put(2, new User("Katja"));
        users.put(3, new User("Anzella"));
        users.put(4, new User("Kolja"));
    }

    public Map<Integer,User> getAll(){
        return users;
    }

    public User getUserById(long id){
        Integer userId = Integer.valueOf(String.valueOf(id));
        if(users.containsKey(userId)){
            User user = users.get(userId);
            return user;

        }
        return null;
    }
}