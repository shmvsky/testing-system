package ru.shmvsky.testingsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.shmvsky.testingsystem.entity.Role;
import ru.shmvsky.testingsystem.entity.User;
import ru.shmvsky.testingsystem.pojo.RegistrationForm;
//import ru.shmvsky.testingsystem.pojo.UserInfo;
import ru.shmvsky.testingsystem.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void register(RegistrationForm registrationForm) {

        String fullname = registrationForm.getFullname();
        String username = registrationForm.getUsername();
        String password = passwordEncoder.encode(registrationForm.getPassword());

        userRepository.save(
                User.builder()
                        .fullname(fullname)
                        .username(username)
                        .password(password)
                        .role(Role.STUDENT)
                        .build()
        );

    }

//    public UserInfo getUserInfo(String username) {
//        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
//        return new UserInfo(user);
//    }

}
