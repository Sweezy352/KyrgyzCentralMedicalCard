package com.example.kyrgyzstancentralmedicalcard.services.impl;

import com.example.kyrgyzstancentralmedicalcard.entity.NewsPictureFiles;
import com.example.kyrgyzstancentralmedicalcard.repository.NewsPictureFilesRepository;
import com.example.kyrgyzstancentralmedicalcard.services.MinIoService;
import com.example.kyrgyzstancentralmedicalcard.services.PictureFileMinIoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@Service
@RequiredArgsConstructor
public class PictureFileMinIoServiceImpl implements PictureFileMinIoService {
    private final MinIoService minIoService;
    @Value("${minio.bucket.name.news}")
    private String bucketName;
    private final NewsPictureFilesRepository newsPictureFilesRepository;


    @Override
    public InputStream streamFile(String fileName) {
        return minIoService.streamFile(bucketName, fileName);
    }

    @Override
    public String getContentType(String fileName) {
        return minIoService.getContentType(bucketName, fileName);
    }

    @Override
    public void upload(MultipartFile file) {
        minIoService.upload(file, bucketName);
    }

    @Override
    public NewsPictureFiles getById(Long id) {
        return newsPictureFilesRepository.findById(id).orElseThrow(() -> new RuntimeException("Файл не найден"));
    }

    @Override
    public NewsPictureFiles getByFileName(String fileName) {
        return  newsPictureFilesRepository.findByOriginalFileName(fileName).orElseThrow(() -> new RuntimeException("Файл не найден"));
    }
}
