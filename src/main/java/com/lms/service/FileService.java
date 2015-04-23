package com.lms.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    public String saveImage(MultipartFile file, Long lessonId);

    public void deleteImage(String fileUrl);

    public Long saveAudio(MultipartFile file, Long lessonId);

    public void deleteAudio(Long id);
}
