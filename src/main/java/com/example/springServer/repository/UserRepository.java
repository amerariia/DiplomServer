package com.example.springServer.repository;

import com.example.springServer.entity.Group;
import com.example.springServer.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByEmailAndPassword(String email, String password);
    List<User> findAllByGroup_Id(Integer id);
    List<User> findAllByGroup_IdIn(List<Integer> ids);
}
