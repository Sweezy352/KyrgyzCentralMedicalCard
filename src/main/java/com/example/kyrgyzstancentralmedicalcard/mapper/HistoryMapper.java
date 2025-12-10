package com.example.kyrgyzstancentralmedicalcard.mapper;

import com.example.kyrgyzstancentralmedicalcard.dto.request.HistoryRequest;
import com.example.kyrgyzstancentralmedicalcard.dto.response.HistoryResponse;
import com.example.kyrgyzstancentralmedicalcard.entity.History;
import com.example.kyrgyzstancentralmedicalcard.entity.User;
import com.example.kyrgyzstancentralmedicalcard.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class HistoryMapper extends BaseMapperImpl<History, HistoryRequest, HistoryResponse> {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public HistoryMapper(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public History toEntity(HistoryRequest request) {
        History entity = mapFields(request, new History());
        if (request.getUserId() != null) {
            User user = userRepository.findById(request.getUserId())
                    .orElseThrow(() -> new RuntimeException("User (userId) not found with ID: " + request.getUserId()));
            entity.setUser(user);
        }
        if (request.getUserDocId() != null) {
            User userDoc = userRepository.findById(request.getUserDocId())
                    .orElseThrow(() -> new RuntimeException("User (userDocId) not found with ID: " + request.getUserDocId()));
            entity.setUserDoc(userDoc);
        }
        return entity;
    }

    @Override
    public HistoryResponse toResponse(History entity) {
        HistoryResponse response = mapFields(entity, new HistoryResponse());
        if (entity.getUser() != null) {
            response.setUser(userMapper.toResponse(entity.getUser()));
        }
        if (entity.getUserDoc() != null) {
            response.setUserDoc(userMapper.toResponse(entity.getUserDoc()));
        }
        return response;
    }
}

