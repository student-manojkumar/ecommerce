package com.manoj.ecomm.service;

import com.manoj.ecomm.model.User;
import com.manoj.ecomm.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User registerUser(User user) {

        try{
            User newUser = userRepository.save(user);
            System.out.println("User Added to database");
            return newUser;

        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return  null;
    }

    public User loginUser(String email, String password) {
        //check if user is there or not
        User user= userRepository.findByEmail(email);

        if(user!=null && user.getPassword().equals(password))
        {
            return user;
        }
          return null; // invaild creadentials
    }

    public List<User> getAllUsers() {

        return  userRepository.findAll();
    }
}
