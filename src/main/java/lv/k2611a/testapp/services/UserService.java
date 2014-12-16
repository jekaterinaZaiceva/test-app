package lv.k2611a.testapp.services;


import lv.k2611a.testapp.services.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import lv.k2611a.testapp.domain.User;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;


@Service
public class UserService {

    @Autowired
    BlogService blogService;
    private Map<Long, User> users;
    private Map<String, User> userNameMap;
    private long userId = 0L;

    @PostConstruct
    public void init() throws UserAlreadyExistException, DublicatedSymbolException, SmallPasswodsException {
        users = new HashMap<Long, User>();
        userNameMap = new HashMap<String, User>();

        registerUser("Kirill", "passKirill");
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
        userNameMap.put(u.getName(), u);
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

    public User getUserByName(String name) {
        String userName = name;
        if (userNameMap.containsKey(userName)) {
            User user = userNameMap.get(userName);
            return user;
        }
        return null;
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

    public void deleteUser(long userId) {
        User user = getUserById(userId);
        users.remove(userId);
        userNameMap.remove(user);

         blogService.deleteUserBlogs(userId);

    }
    public void registerUser(String userName, String password) throws UserAlreadyExistException, DublicatedSymbolException, SmallPasswodsException {
        Long id=userId++;
        if (userNameMap.containsKey(userName)) {
            throw new UserAlreadyExistException();
        }
        if(!validateDublicatedSymbols(password)){
             throw new DublicatedSymbolException();
        }
         if(!validLenghtPassword(password)){
            throw new SmallPasswodsException();
        }

        User user = new User(id,userName,password);
        users.put(id,user);
        userNameMap.put(userName, user);

    }


    private boolean validateDublicatedSymbols(final String password) {
        String passwordLower = password.toLowerCase();
        int countOfDublicated;
        char symbol;
        for (int i = 0; i < passwordLower.length(); ++i) {
            countOfDublicated = 0;
            symbol = password.charAt(i);
            for (int j = i; j < passwordLower.length(); ++j) {
                if (passwordLower.charAt(j) == symbol) {
                    if (++countOfDublicated > 4) return false;
                }
            }
        }
        return true;
    }
    private boolean validLenghtPassword(final String password){
        if(password.length()>4){
            return true;
        }
        else return  false;
    }

}