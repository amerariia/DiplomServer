package com.example.springServer.service;

import com.example.springServer.entity.User;
import org.springframework.security.authentication.AuthenticationProvider;

import java.util.List;
import java.util.Optional;

public interface UserService extends AuthenticationProvider {
    List<User> getAll();
    User getById(Integer id);
    User save(User user);
    User getByEmailAndPassword(String email, String password);
    void deleteById(Integer id);
}
