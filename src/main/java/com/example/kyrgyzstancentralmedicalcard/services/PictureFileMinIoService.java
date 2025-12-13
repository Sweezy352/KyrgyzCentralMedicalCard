package com.example.kyrgyzstancentralmedicalcard.services;

import com.example.kyrgyzstancentralmedicalcard.entity.NewsPictureFiles;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

public interface PictureFileMinIoService {
    InputStream streamFile(String fileName);
    String getContentType(String fileName);
    void upload(MultipartFile file);
    NewsPictureFiles getById(Long id);
    NewsPictureFiles getByFileName(String fileName);
}
