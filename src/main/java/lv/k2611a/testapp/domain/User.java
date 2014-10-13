package lv.k2611a.testapp.domain;


/**
 * доменное имя юзера
 */
public class User {

   private String name;

    public User(String name){
        this.name = name;
    }

    public String getName() {
     return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
