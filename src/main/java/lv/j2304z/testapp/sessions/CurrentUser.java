package lv.j2304z.testapp.sessions;

/**
 * Created by jekaterina.zaiceva on 17.11.2014.
 */
public interface CurrentUser {
    String getLogin();
    Long getId();

    void setLogin(String login);
    void setId(Long id);
}
