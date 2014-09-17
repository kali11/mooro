package com.lms.model.dao;

import com.lms.model.entity.User;

public interface UserDao {
    public User getUser(Integer id);

    public User getUser(String login);
}
