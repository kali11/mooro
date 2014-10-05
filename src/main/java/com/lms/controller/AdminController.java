package com.lms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lms.model.entity.User;
import com.lms.service.RoleService;
import com.lms.service.UserService;

/**
 * Controller responsible for system administration
 *
 * @author Piotr Kalinowski
 *
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        return "admin/index";
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String users(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "admin/users";
    }

    @RequestMapping(value = "/users/add", method = RequestMethod.GET)
    public String addUser(@ModelAttribute User user, BindingResult result, Model model) {
        model.addAttribute("roles", roleService.getAllRolesMap());
        return "admin/edit_user";
    }

    @RequestMapping(value = "/users/add", method = RequestMethod.POST)
    public String saveUser(@ModelAttribute User user, @RequestParam("roleId") String roleId, Model model) {
        userService.addUser(user, roleId);
        return "redirect:/admin/users";
    }
}
