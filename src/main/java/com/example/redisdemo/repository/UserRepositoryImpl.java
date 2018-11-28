package com.example.redisdemo.repository;

import com.example.redisdemo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import java.util.Map;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private RedisTemplate<String, User> redisTemplate;

    private HashOperations hashOperations; // This is used to access the redis template


    public UserRepositoryImpl(RedisTemplate<String, User> redisTemplate){
        this.redisTemplate = redisTemplate;
        hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public User saveUser(User user) {
        hashOperations.put("USER", user.getId(), user);
        return user;
    }

    @Override
    public User findById(String id) {
        return (User)hashOperations.get("USER", id);
    }

    @Override
    public Map<String,User> findAll() {
        return hashOperations.entries("USER");
    }

    @Override
    public void update(User user) {
        saveUser(user);
    }

    @Override
    public void delete(String id) {
       hashOperations.delete("USER", id);
    }
}
