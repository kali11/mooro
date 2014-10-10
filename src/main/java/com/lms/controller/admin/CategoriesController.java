package com.lms.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller responsible for managing course categories
 *
 * @author Piotr Kalinowski
 *
 */
@Controller
@RequestMapping("/admin/categories")
public class CategoriesController {

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        return "admin/categories/categories";
    }
}
