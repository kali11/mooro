package com.lms.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lms.model.entity.Element;
import com.lms.model.entity.ElementText;
import com.lms.model.entity.ElementVideo;
import com.lms.model.entity.Lesson;
import com.lms.service.ElementService;
import com.lms.service.LessonService;

@Controller
@RequestMapping("/elements")
public class ElementController {

    @Autowired
    private LessonService lessonService;

    @Autowired
    private ElementService elementService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String ajaxAdd(@ModelAttribute Element element, @RequestParam("lessonId") Long lessonId, Model model) {
        model.addAttribute("lessonId", lessonId);
        return "elements/edit-element";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute Element element, @RequestParam("lessonId") Long lessonId,
            @RequestParam("elementType") String elementType, HttpServletRequest request) {
        Lesson lesson = lessonService.get(lessonId);
        element.setLesson(lesson);
        elementService.save(element, elementType, request);
        return "redirect:/modules/" + lesson.getModule().getId() + "?lessonId=" + lessonId;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public String ajaxDisplay(@PathVariable Long id, Model model) {
        Element element = elementService.get(id);
        String elementType = "";
        if (element instanceof ElementText) {
            elementType = "text";
        } else if (element instanceof ElementVideo) {
            elementType = "video";
        }
        model.addAttribute("elementType", elementType);
        model.addAttribute("element", element);
        return "elements/display-element";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable Long id) {
        Element element = elementService.get(id);
        elementService.delete(element);
        Lesson lesson = element.getLesson();
        return "redirect:/modules/" + lesson.getModule().getId() + "?lessonId=" + lesson.getId();
    }
}
