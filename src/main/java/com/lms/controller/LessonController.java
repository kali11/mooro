package com.lms.controller;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
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

    @Autowired
    private Environment env;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(@ModelAttribute Lesson lesson, @RequestParam("moduleId") Long moduleId, Model model) {
        model.addAttribute("moduleId", moduleId);
        return "lessons/edit-lesson";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute Lesson lesson, @RequestParam("moduleId") Long moduleId) {
        lesson.setModule(moduleService.get(moduleId));
        Long lessonId = lessonService.save(lesson);
        createLessonDirectories(lessonId);
        return "redirect:/modules/" + moduleId.toString();
    }

    private void createLessonDirectories(Long id) {
        File dir = new File(env.getProperty("filesPath") + id);
        if (!dir.exists()) {
            dir.mkdir();
            new File(env.getProperty("filesPath") + id + "/images").mkdir();
            new File(env.getProperty("filesPath") + id + "/audio").mkdir();
            new File(env.getProperty("filesPath") + id + "/others").mkdir();
        }
    }
}
