package com.lms.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.lms.service.FileService;

@Controller
@RequestMapping("/files")
public class FileController {

    @Autowired
    private FileService fileService;

    // TODO: ograniczyć wielkosc przesłanych plików oraz rozszerzenia
    @RequestMapping(value = "/upload/image", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> ajaxUploadImage(@RequestParam("file") MultipartFile file,
            @RequestParam("lessonId") Long lessonId, HttpServletRequest request) {
        String uuid = fileService.saveImage(file, lessonId);
        Map<String, Object> jsonResult = new HashMap<>();
        jsonResult.put("link", request.getContextPath() + "/files/" + lessonId + "/images/" + uuid);
        return jsonResult;
    }

    @RequestMapping(value = "/delete/image", method = RequestMethod.POST)
    public ModelAndView ajaxDeleteImage(@RequestParam("src") String fileUrl) {
        fileService.deleteImage(fileUrl);
        return null;
    }

    @RequestMapping(value = "/upload/audio", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> ajaxUploadAudio(@RequestParam("file") MultipartFile file,
            @RequestParam("lessonId") Long lessonId, @RequestParam(value = "oldFileId", required = false) Long oldFileId) {
        Map<String, Object> jsonResult = new HashMap<>();
        jsonResult.put("fileName", file.getOriginalFilename());
        jsonResult.put("fileId", fileService.saveAudio(file, lessonId));
        if (oldFileId != null) {
            fileService.deleteAudio(oldFileId);
        }
        return jsonResult;
    }
}
