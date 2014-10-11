package com.lms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lms.model.entity.Course;
import com.lms.service.CategoryService;
import com.lms.service.CourseService;

@Controller
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(method = RequestMethod.GET)
    public String courses(Model model) {
        model.addAttribute("courses", courseService.getAll());
        return "courses/courses";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(@ModelAttribute Course course, Model model) {
        model.addAttribute("categories", categoryService.getAllCategoriesMap());
        return "courses/edit_course";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute Course course, @RequestParam("categoryIds") List<String> categoryId, Model model) {
        courseService.save(course, categoryId);
        return "redirect:/courses";
    }

}
