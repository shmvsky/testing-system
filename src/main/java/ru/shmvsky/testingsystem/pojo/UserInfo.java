package ru.shmvsky.testingsystem.pojo;

import lombok.Data;
import ru.shmvsky.testingsystem.entity.User;

@Data
public class UserInfo {

    private String fullname;
    private String username;
    private String role;

    public UserInfo(User user) {
        this.fullname = user.getFullname();
        this.username = user.getUsername();
        this.role = user.getRole().toString();
    }

}
