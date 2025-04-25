package com.manoj.ecomm.controller;

import com.manoj.ecomm.model.User;
import com.manoj.ecomm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User registerUser(@RequestBody User user)
    {
       return userService.registerUser(user);
    }

    @PostMapping("/login")
    public User login(@RequestBody User user)
    {
        return userService.loginUser(user.getEmail(),user.getPassword());
    }
    @GetMapping
    public List<User>getAllUsers()
    {
        return userService.getAllUsers();
    }

}
