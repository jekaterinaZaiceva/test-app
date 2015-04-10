package lv.j2304z.testapp.dto;

import lv.j2304z.testapp.domain.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jekaterina.zaiceva on 27.03.15.
 */
public class UsersResponse {

    List<UserDTO> users = new ArrayList<UserDTO>();


    public List<UserDTO> getUsers() {
        return users;
    }

    public void setUsers(List<UserDTO> users) {
        this.users = users;
    }

}
