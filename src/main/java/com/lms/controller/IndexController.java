package com.lms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lms.model.dao.UserDao;
import com.lms.model.entity.User;
import com.lms.utils.PasswordService;

@Controller
@RequestMapping("/")
class IndexController {

    @Autowired
    UserDao userDao;

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("MsTime", System.currentTimeMillis());
        return "index";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String login(Model model,
            @RequestParam("login") String login,
            @RequestParam("password") String password) {

        String encryptedPassword = PasswordService.encryptSHA(password);
        User user = userDao.getUser(login);
        if (user != null && user.getPassword().equals(encryptedPassword)) {
            return "index";
        } else {
            model.addAttribute("password", user.getPassword());
            return "index";
        }
    }
}
