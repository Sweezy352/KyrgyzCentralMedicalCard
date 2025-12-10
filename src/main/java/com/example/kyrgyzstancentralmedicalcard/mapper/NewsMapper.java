package com.example.kyrgyzstancentralmedicalcard.mapper;

import com.example.kyrgyzstancentralmedicalcard.dto.request.NewsRequest;
import com.example.kyrgyzstancentralmedicalcard.dto.response.NewsResponse;
import com.example.kyrgyzstancentralmedicalcard.entity.News;
import com.example.kyrgyzstancentralmedicalcard.entity.User;
import com.example.kyrgyzstancentralmedicalcard.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class NewsMapper extends BaseMapperImpl<News, NewsRequest, NewsResponse> {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public NewsMapper(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public News toEntity(NewsRequest request) {
        News entity = mapFields(request, new News());

        if (request.getUserId() != null) {
            User user = userRepository.findById(request.getUserId())
                    .orElseThrow(() -> new RuntimeException("User (userId) not found with ID: " + request.getUserId()));
            entity.setUser(user);
        }
        return entity;
    }

    @Override
    public NewsResponse toResponse(News entity) {
        NewsResponse response = mapFields(entity, new NewsResponse());
        if (entity.getUser() != null) {
            response.setUser(userMapper.toResponse(entity.getUser()));
        }
        return response;
    }
}

