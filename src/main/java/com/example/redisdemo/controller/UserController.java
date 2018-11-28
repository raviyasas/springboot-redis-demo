package com.example.redisdemo.controller;

import com.example.redisdemo.model.User;
import com.example.redisdemo.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/rest/users")
public class UserController {

    private UserRepository userRepository;

    private UserController(UserRepository userRepository){
        this.userRepository = userRepository;
    }


    @PostMapping("/add")
    public User addUser(@RequestBody User user){
        return userRepository.saveUser(user);
    }

    @GetMapping("/update/{id}/{name}")
    public User updateUser(@PathVariable("id") final String id, @PathVariable("name") final String name){
        userRepository.update(new User(id,name,1000L));
        return userRepository.findById(id);
    }

    @GetMapping("/all")
    public Map<String,User> getUser(){
        return userRepository.findAll();
    }

    @GetMapping("/remove/{id}")
    public void deleteUser(@PathVariable("id") String id){
        userRepository.delete(id);
    }

}
