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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.lms.model.entity.Course;

@Controller
@RequestMapping("/elements")
public class ElementController {

    @Autowired
    private Environment env;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String ajaxAdd(@ModelAttribute Course course, Model model) {
        // model.addAttribute("categories", categoryService.getAllCategoriesMap());
        return "elements/edit-element";
    }

    // TODO: ograniczyć wielkosc przesłanych plików
    @RequestMapping(value = "/upload/image", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> ajaxUploadImage(@RequestParam("file") MultipartFile file,
            HttpServletRequest request) {
        String uuid = UUID.randomUUID().toString() + "." + FilenameUtils.getExtension(file.getOriginalFilename());
        String filePath = env.getProperty("filesPath") + uuid;
        try {
            FileCopyUtils.copy(file.getBytes(), new FileOutputStream(filePath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<String, Object> jsonResult = new HashMap<>();
        jsonResult.put("link", request.getContextPath() + "/files/" + uuid);
        return jsonResult;
    }

    @RequestMapping(value = "/delete/image", method = RequestMethod.POST)
    public ModelAndView ajaxDeleteImage(@RequestParam("src") String fileUrl) {
        String filePath = env.getProperty("filesPath") + FilenameUtils.getBaseName(fileUrl) + "."
                + FilenameUtils.getExtension(fileUrl);
        try {
            Files.deleteIfExists(Paths.get(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
