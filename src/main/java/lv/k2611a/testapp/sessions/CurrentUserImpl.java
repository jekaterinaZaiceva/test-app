package lv.k2611a.testapp.sessions;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 * Created by jekaterina.zaiceva on 17.11.2014.
 */

@Scope(value = "session", proxyMode = ScopedProxyMode.INTERFACES)
@Component
public class CurrentUserImpl implements CurrentUser{
    private String login;
    private Long id;

    @Override
    public String getLogin() {
        return login;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setLogin(String login) {
        this.login=login;

    }

    @Override
    public void setId(Long id) {
        this.id=id;

    }
}
