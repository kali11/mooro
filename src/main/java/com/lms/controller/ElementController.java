package com.lms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lms.model.entity.Course;

@Controller
@RequestMapping("/elements")
public class ElementController {

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String ajaxAdd(@ModelAttribute Course course, Model model) {
        // model.addAttribute("categories", categoryService.getAllCategoriesMap());
        return "elements/edit-element";
    }
}
