package com.user.service.service;

import com.user.service.entities.User;

import java.util.List;

public interface UserService {

    // create user
    User createUser(User user);

    // get all user
     List<User> getAllUsers();

    //get single user
    User getUserById(String id);

    //delete single user
    void deleteById(String userId);
}
