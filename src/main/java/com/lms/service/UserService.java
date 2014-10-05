package com.lms.service;

import java.util.List;

import com.lms.model.entity.User;

public interface UserService {

    public void addUser(User user);

    public void addUser(User user, String roleId);

    public List<User> getAllUsers();

}
