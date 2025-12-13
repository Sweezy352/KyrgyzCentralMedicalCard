package com.example.kyrgyzstancentralmedicalcard.services.impl;

import com.example.kyrgyzstancentralmedicalcard.entity.Company;
import com.example.kyrgyzstancentralmedicalcard.entity.News;
import com.example.kyrgyzstancentralmedicalcard.entity.NewsPictureFiles;
import com.example.kyrgyzstancentralmedicalcard.entity.User;
import com.example.kyrgyzstancentralmedicalcard.repository.NewsPictureFilesRepository;
import com.example.kyrgyzstancentralmedicalcard.repository.NewsRepository;
import com.example.kyrgyzstancentralmedicalcard.services.AuthService;
import com.example.kyrgyzstancentralmedicalcard.services.NewsService;
import com.example.kyrgyzstancentralmedicalcard.services.PictureFileMinIoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {
    private final NewsRepository newsRepository;
    private final AuthService authService;
    private final PictureFileMinIoService pictureFileMinIoService;
    private final NewsPictureFilesRepository newsPictureFilesRepository;

    @Override
    public List<News> getAllNews() {
        return newsRepository.findAll();
    }

    @Override
    public News createNews(News news, MultipartFile multipartFile) {
        User user = authService.getCurrentUser();
        news.setUser(user);
        pictureFileMinIoService.upload(multipartFile);
        News newsSaved =  newsRepository.save(news);
        NewsPictureFiles newsPictureFiles = new NewsPictureFiles();
        newsPictureFiles.setOriginalFileName(multipartFile.getOriginalFilename());
        newsPictureFiles.setMimeType(multipartFile.getContentType());
        newsSaved.setNewsPictureFiles(List.of(newsPictureFilesRepository.save(newsPictureFiles)));
        return newsSaved;

        //AutoEffect
    }

    @Override
    public News getById(Long id) {
        return newsRepository.findById(id).orElseThrow(() -> new RuntimeException("Такой новости не существует"));
    }

    @Override
    public List<News> getByName(String name) {
        return newsRepository.findByName(name).orElseThrow(() -> new RuntimeException("Такая новость не найдена"));
    }

    @Override
    public News updateNews(News news) {
        return newsRepository.save(news);
    }
}
