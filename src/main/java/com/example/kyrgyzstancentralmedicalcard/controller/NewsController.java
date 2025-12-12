package com.example.kyrgyzstancentralmedicalcard.controller;

import com.example.kyrgyzstancentralmedicalcard.dto.request.NewsRequest;
import com.example.kyrgyzstancentralmedicalcard.dto.response.NewsResponse;
import com.example.kyrgyzstancentralmedicalcard.entity.News;
import com.example.kyrgyzstancentralmedicalcard.mapper.NewsMapper;
import com.example.kyrgyzstancentralmedicalcard.services.NewsService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/news")
@RequiredArgsConstructor
public class NewsController {
    private final NewsService newsService;
    private final NewsMapper newsMapper;

    @PostMapping("/create-news")
    public ResponseEntity<NewsResponse> createNews(@RequestBody NewsRequest newsRequest){
        return ResponseEntity.ok(newsMapper.toDto(newsService.createNews(newsMapper.toEntity(newsRequest))));
    }

    @GetMapping("/get-by-id/{id}]")
    public ResponseEntity<NewsResponse> getNewsById(@PathVariable("id") Long id){
        return ResponseEntity.ok(newsMapper.toDto(newsService.getById(id)));
    }

    @GetMapping("/get-by-name")
    public ResponseEntity<List<NewsResponse>> getNewsByName(@RequestParam String name){
        return ResponseEntity.ok(newsService.getByName(name).stream().map(newsMapper::toDto).toList());
    }

    @GetMapping("/get-all-news")
    public ResponseEntity<List<NewsResponse>> getAllNews(){
        return ResponseEntity.ok(newsService.getAllNews().stream().map(newsMapper::toDto).toList());
    }

    @PutMapping("/update-news")
    public ResponseEntity<NewsResponse> updateNews(@RequestBody News news){
        return ResponseEntity.ok(newsMapper.toDto(newsService.updateNews(news)));
    }
}
