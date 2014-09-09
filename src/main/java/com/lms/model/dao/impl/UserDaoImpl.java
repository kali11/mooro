package com.lms.model.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.lms.model.dao.UserDao;
import com.lms.model.entity.User;

public class UserDaoImpl implements UserDao{
    
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public User getUser(Integer id){
        Session session = sessionFactory.openSession();
        User user = (User) session.get(User.class, id);
        session.close();
        return user;
    }

}
