package com.lms.model.dao.impl;

import org.springframework.stereotype.Repository;

import com.lms.model.dao.UserDao;
import com.lms.model.dao.generic.LmsGenericDaoImpl;
import com.lms.model.entity.User;

@Repository
public class UserDaoImpl extends LmsGenericDaoImpl<User, Integer> implements UserDao {

}
