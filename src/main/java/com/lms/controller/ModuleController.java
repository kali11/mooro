package com.lms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lms.model.entity.Module;
import com.lms.service.CourseService;
import com.lms.service.ModuleService;

@Controller
@RequestMapping("/modules")
public class ModuleController {

    @Autowired
    private ModuleService moduleService;

    @Autowired
    private CourseService courseService;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String modules(@ModelAttribute Module module, @RequestParam("courseId") Long courseId, Model model) {
        model.addAttribute("courseId", courseId);
        return "modules/edit_module";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute Module module, @RequestParam("courseId") Long courseId) {
        module.setCourse(courseService.get(courseId));
        moduleService.save(module);
        return "redirect:/courses/" + courseId.toString();
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable Long id, Model model, @RequestParam("courseId") Long courseId) {
        model.addAttribute("module", moduleService.get(id));
        model.addAttribute("courseId", courseId);
        return "modules/edit_module";
    }
}
