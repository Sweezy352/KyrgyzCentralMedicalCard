package com.example.kyrgyzstancentralmedicalcard.services;

import com.example.kyrgyzstancentralmedicalcard.entity.News;
import com.example.kyrgyzstancentralmedicalcard.entity.User;

import java.util.List;

public interface NewsService {
    List<News> getAllNews();
    News createNews(News news);
    News getById(Long id);
    List<News> getByName(String name);
    News updateNews(News news);
}
