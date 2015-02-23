package com.lms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lms.model.entity.Module;
import com.lms.service.CourseService;
import com.lms.service.ModuleService;

@Controller
@RequestMapping("/courses/{courseId}/modules")
public class ModuleController {

    @Autowired
    private ModuleService moduleService;

    @Autowired
    private CourseService courseService;

    @ModelAttribute
    public Long courseId(@PathVariable Long courseId) {
        return courseId;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String modules(@ModelAttribute Module module, Model model) {
        return "modules/edit_module";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute Module module, @PathVariable Long courseId) {
        module.setCourse(courseService.get(courseId));
        moduleService.save(module);
        return "redirect:/courses/" + courseId.toString();
    }
}
