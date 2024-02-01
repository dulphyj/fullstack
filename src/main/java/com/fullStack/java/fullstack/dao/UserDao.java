package com.fullStack.java.fullstack.dao;

import com.fullStack.java.fullstack.models.User;

import java.util.List;

public interface UserDao {
    List<User> getUsers();

    void delete(Long id);

    void post(User user);

    User verifyCredentials(User user);
}
