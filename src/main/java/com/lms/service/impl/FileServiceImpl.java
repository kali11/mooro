package com.lms.service.impl;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.lms.model.dao.FileDao;
import com.lms.model.entity.File;
import com.lms.service.FileService;

@Service
@Transactional
public class FileServiceImpl implements FileService {

    @Autowired
    private Environment env;

    @Autowired
    private FileDao fileDao;

    @Override
    public String saveImage(MultipartFile file, Long lessonId) {
        String uuid = UUID.randomUUID().toString() + "." + FilenameUtils.getExtension(file.getOriginalFilename());
        String filePath = env.getProperty("filesPath") + lessonId + "/images/" + uuid;
        try {
            FileCopyUtils.copy(file.getBytes(), new FileOutputStream(filePath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return uuid;
    }

    @Override
    public void deleteImage(String fileUrl) {
        String filePath = env.getProperty("filesPath") + fileUrl.substring(fileUrl.indexOf("files") + 6);
        try {
            Files.deleteIfExists(Paths.get(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Long saveAudio(MultipartFile file, Long lessonId) {
        String uuid = UUID.randomUUID().toString() + "." + FilenameUtils.getExtension(file.getOriginalFilename());
        String filePath = env.getProperty("filesPath") + lessonId + "/audio/" + uuid;
        try {
            FileCopyUtils.copy(file.getBytes(), new FileOutputStream(filePath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        File audioFile = new File();
        audioFile.setLocation(filePath);
        audioFile.setOriginalName(file.getOriginalFilename());
        audioFile.setUuid(uuid);
        fileDao.save(audioFile);
        return audioFile.getId();
    }

    @Override
    public void deleteAudio(Long id) {
        File file = fileDao.find(id);
        try {
            Files.deleteIfExists(Paths.get(file.getLocation()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        fileDao.remove(file);
    }

}
