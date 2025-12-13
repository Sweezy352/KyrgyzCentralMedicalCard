package com.example.kyrgyzstancentralmedicalcard.services;

import com.example.kyrgyzstancentralmedicalcard.entity.News;
import com.example.kyrgyzstancentralmedicalcard.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface NewsService {
    List<News> getAllNews();
    News createNews(News news, MultipartFile multipartFile);
    News getById(Long id);
    List<News> getByName(String name);
    News updateNews(News news);
}
