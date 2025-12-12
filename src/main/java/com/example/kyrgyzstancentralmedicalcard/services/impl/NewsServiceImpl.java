package com.example.kyrgyzstancentralmedicalcard.services.impl;

import com.example.kyrgyzstancentralmedicalcard.entity.Company;
import com.example.kyrgyzstancentralmedicalcard.entity.News;
import com.example.kyrgyzstancentralmedicalcard.entity.User;
import com.example.kyrgyzstancentralmedicalcard.repository.NewsRepository;
import com.example.kyrgyzstancentralmedicalcard.services.AuthService;
import com.example.kyrgyzstancentralmedicalcard.services.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {
    private final NewsRepository newsRepository;
    private final AuthService authService;

    @Override
    public List<News> getAllNews() {
        return newsRepository.findAll();
    }

    @Override
    public News createNews(News news) {
        User user = authService.getCurrentUser();
        news.setUser(user);
        return newsRepository.save(news);
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
