package com.example.kyrgyzstancentralmedicalcard.controller;

import com.example.kyrgyzstancentralmedicalcard.dto.request.NewsRequest;
import com.example.kyrgyzstancentralmedicalcard.dto.response.NewsPictureFileDtoResponse;
import com.example.kyrgyzstancentralmedicalcard.dto.response.NewsResponse;
import com.example.kyrgyzstancentralmedicalcard.entity.News;
import com.example.kyrgyzstancentralmedicalcard.entity.NewsPictureFiles;
import com.example.kyrgyzstancentralmedicalcard.mapper.NewsMapper;
import com.example.kyrgyzstancentralmedicalcard.mapper.NewsPictureFileMapper;
import com.example.kyrgyzstancentralmedicalcard.services.NewsService;
import com.example.kyrgyzstancentralmedicalcard.services.PictureFileMinIoService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/news")
@RequiredArgsConstructor
public class NewsController {
    private final NewsService newsService;
    private final NewsMapper newsMapper;
    private final PictureFileMinIoService pictureFileMinIoService;
    private final NewsPictureFileMapper newsPictureFileMapper;

    @PostMapping("/create-news")
    public ResponseEntity<NewsResponse> createNews(@RequestBody NewsRequest newsRequest, @RequestParam("file")MultipartFile multipartFile){
        return ResponseEntity.ok(newsMapper.toDto(newsService.createNews(newsMapper.toEntity(newsRequest), multipartFile)));
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

    @GetMapping("/get-file-by-id/{id}")
    public ResponseEntity<InputStreamResource> getById(@PathVariable("id") Long id) {
        return ResponseEntity
                .ok()
                .contentType(MediaType.parseMediaType(pictureFileMinIoService.getContentType(pictureFileMinIoService.getById(id).getOriginalFileName())))
                .body(new InputStreamResource(pictureFileMinIoService.streamFile(pictureFileMinIoService.getById(id).getOriginalFileName())));
    }

    @GetMapping("/get-file-by-name/{file_name}")
    public ResponseEntity<InputStreamResource> getByFileName(@PathVariable("file_name") String fileName) {
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(pictureFileMinIoService.getContentType(fileName)))
                .body(new InputStreamResource(pictureFileMinIoService.streamFile(fileName)));
    }

    @GetMapping("/get-picture-by-id/{id}")
    public ResponseEntity<NewsPictureFileDtoResponse> getImagesById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(newsPictureFileMapper.toDto(pictureFileMinIoService.getById(id)));
    }

    @GetMapping("/get-picture-by-fileName/{file_name}")
    public ResponseEntity<NewsPictureFileDtoResponse> getImagesByFileName(@PathVariable("file_name") String fileName) {
        return ResponseEntity.ok(newsPictureFileMapper.toDto(pictureFileMinIoService.getByFileName(fileName)));
    }
}
