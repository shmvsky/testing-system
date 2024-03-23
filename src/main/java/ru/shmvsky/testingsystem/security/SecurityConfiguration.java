package ru.shmvsky.testingsystem.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/login", "/registration").permitAll()
                .requestMatchers("/my-tests/**").hasRole("TEACHER")
                .anyRequest().authenticated());

        httpSecurity.formLogin(login -> login
                .defaultSuccessUrl("/home"));

        httpSecurity.logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login"));

        return httpSecurity.build();

    }

}
