package ru.shmvsky.testingsystem.pojo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegistrationForm {
    private String fullname;
    private String username;
    private String password;
}
