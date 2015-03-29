package com.lms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lms.model.entity.Lesson;
import com.lms.service.LessonService;
import com.lms.service.ModuleService;

@Controller
@RequestMapping("/lessons")
public class LessonController {

    @Autowired
    private ModuleService moduleService;

    @Autowired
    private LessonService lessonService;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(@ModelAttribute Lesson lesson, @RequestParam("moduleId") Long moduleId, Model model) {
        model.addAttribute("moduleId", moduleId);
        return "lessons/edit-lesson";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute Lesson lesson, @RequestParam("moduleId") Long moduleId) {
        lesson.setModule(moduleService.get(moduleId));
        lessonService.save(lesson);
        return "redirect:/modules/" + moduleId.toString();
    }
}
