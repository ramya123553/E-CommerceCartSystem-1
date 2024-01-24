package com.example.ecommerce.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecommerce.dao.UserDao;
import com.example.ecommerce.models.User;

import java.util.List;


@Service
public class UserService {

    @Autowired
    private UserDao userDao; // Updated field name to follow Java naming conventions

    public List<User> getUsers() {
        return this.userDao.getAllUsers(); // Updated method name
    }

    public User addUser(org.apache.catalina.User user) {
        return (User) this.userDao.saveUser(user);
    }

    public User checkLogin(String username, String password) {
        return this.userDao.getUser(username, password);
    }

    public boolean checkUserExists(String username) {
        return this.userDao.userExists(username);
    }
    
    public User registerNewUser(String username, String email, String password, String address) {
        // Check if the username already exists
        if (checkUserExists(username)) {
            // Handle scenario where the username is already taken
            return null;
        }

        // Create a new User object with the provided information
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setEmail(email);
        newUser.setPassword(password);
        newUser.setAddress(address);

        // Save the new user to the database
        return this.userDao.saveUser(newUser);
    }
}
