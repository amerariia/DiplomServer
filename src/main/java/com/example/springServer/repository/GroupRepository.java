package com.example.springServer.repository;

import com.example.springServer.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group, Integer> {
    void deleteAllByIdIn(List<Integer> ids);
}
