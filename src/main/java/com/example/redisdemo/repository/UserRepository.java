package com.example.redisdemo.repository;

import com.example.redisdemo.model.User;
import java.util.Map;

public interface UserRepository {

    User saveUser(User user);
    User findById(String id);
    Map<String,User> findAll();
    void update(User user);
    void delete(String id);
}
