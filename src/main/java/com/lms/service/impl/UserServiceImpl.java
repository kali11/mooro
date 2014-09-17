package com.lms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.genericdao.search.Search;
import com.lms.model.dao.UserDao;
import com.lms.model.entity.User;
import com.lms.service.UserService;
import com.lms.utils.PasswordService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public Boolean loginUser(String login, String password) {
        String encryptedPassword = PasswordService.encryptSHA(password);
        Search search = new Search(User.class).addFilterEqual("login", login);
        User user = userDao.searchUnique(search);
        if (user != null && user.getPassword().equals(encryptedPassword)) {
            return true;
        } else {
            return false;
        }
    }

}
