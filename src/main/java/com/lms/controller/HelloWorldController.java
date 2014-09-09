package com.lms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lms.model.dao.UserDao;
import com.lms.model.dao.impl.UserDaoImpl;

@Controller
public class HelloWorldController {

    @Autowired
    UserDao userDao;

    @RequestMapping("/hello")
    public String hello(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {

        model.addAttribute("name", userDao.getUser(1).getLogin());
        return "helloworld";
    }
}
