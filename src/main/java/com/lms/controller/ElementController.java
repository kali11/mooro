package com.lms.controller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

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
    private Environment env;

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
        switch (elementType) {
        case "text":
            saveText(element, request);
            break;
        case "video":
            saveVideo(element, request);
            break;
        }
        return "redirect:/modules/" + lesson.getModule().getId() + "?lessonId=" + lessonId;
    }

    private void saveText(Element element, HttpServletRequest request) {
        ElementText elementText = new ElementText(element);
        elementText.setText(request.getParameter("text"));
        elementService.save(elementText);
    }

    private void saveVideo(Element element, HttpServletRequest request) {
        ElementVideo elementVideo = new ElementVideo(element);
        elementVideo.setDescription(request.getParameter("description"));
        String src = request.getParameter("src");
        src = src.replace("watch?v=", "embed/");
        elementVideo.setSrc(src);
        elementService.save(elementVideo);
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

    // TODO: ograniczyć wielkosc przesłanych plików oraz rozszerzenia
    @RequestMapping(value = "/upload/image", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> ajaxUploadImage(@RequestParam("file") MultipartFile file,
            @RequestParam("lessonId") Long lessonId, HttpServletRequest request) {
        String uuid = UUID.randomUUID().toString() + "." + FilenameUtils.getExtension(file.getOriginalFilename());
        String filePath = env.getProperty("filesPath") + lessonId + "/images/" + uuid;
        try {
            FileCopyUtils.copy(file.getBytes(), new FileOutputStream(filePath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<String, Object> jsonResult = new HashMap<>();
        jsonResult.put("link", request.getContextPath() + "/files/" + lessonId + "/images/" + uuid);
        return jsonResult;
    }

    @RequestMapping(value = "/delete/image/{lessonId}", method = RequestMethod.POST)
    public ModelAndView ajaxDeleteImage(@RequestParam("src") String fileUrl, @PathVariable Long lessonId) {
        String filePath = env.getProperty("filesPath") + lessonId + "/images/" + FilenameUtils.getBaseName(fileUrl)
                + "." + FilenameUtils.getExtension(fileUrl);
        try {
            Files.deleteIfExists(Paths.get(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
