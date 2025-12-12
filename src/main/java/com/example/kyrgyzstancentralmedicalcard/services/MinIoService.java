package com.example.kyrgyzstancentralmedicalcard.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

public interface MinIoService {
    InputStream streamFile(String bucketName, String fileName);
    String getContentType(String bucketName, String fileName);
    void upload(MultipartFile file, String bucketName);
    boolean fileExists(String bucketName, String fileName);
}
