package com.user.service.service;

import com.user.service.entities.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {

    // create user
    User createUser(User user);

    // get all user
    Page<User> getAllUsers(int page, int size, String sort);

    //get single user
    User getUserById(String id);

    //delete single user
    void deleteById(String userId);
}
