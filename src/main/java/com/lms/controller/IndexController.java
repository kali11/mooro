package com.lms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lms.service.UserService;

@Controller
@RequestMapping("/")
class IndexController {

    @Autowired
    UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("MsTime", System.currentTimeMillis());
        return "index";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String login(Model model,
            @RequestParam("login") String login,
            @RequestParam("password") String password) {

        Boolean logged = userService.loginUser(login, password);
        if (!logged) {
            model.addAttribute("login", "kupabalda");
        }
        return "index";
    }
}
