package com.example.springServer.service.impl;

import com.example.springServer.entity.User;
import com.example.springServer.repository.GroupRepository;
import com.example.springServer.repository.UserRepository;
import com.example.springServer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.Collections.singletonList;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GroupRepository groupRepository;

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User getById(Integer id) { return userRepository.findById(id).orElse(null); }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getByEmailAndPassword(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password)
                .orElseThrow(() -> new RuntimeException("Incorrect email or password"));
    }
    @Override
    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String password = (String) authentication.getCredentials();

        User user = getByEmailAndPassword(email, password);
        Authentication authenticate = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword(),
                singletonList(new SimpleGrantedAuthority(user.getRole().name())));
        SecurityContextHolder.getContext().setAuthentication(authenticate);

        return authenticate;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
