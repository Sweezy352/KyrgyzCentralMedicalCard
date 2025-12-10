package com.example.kyrgyzstancentralmedicalcard.mapper;

import com.example.kyrgyzstancentralmedicalcard.dto.request.DiagnosisRequest;
import com.example.kyrgyzstancentralmedicalcard.dto.response.DiagnosisResponse;
import com.example.kyrgyzstancentralmedicalcard.entity.Diagnosis;
import com.example.kyrgyzstancentralmedicalcard.entity.User;
import com.example.kyrgyzstancentralmedicalcard.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DiagnosisMapper extends BaseMapperImpl<Diagnosis, DiagnosisRequest, DiagnosisResponse> {

    private UserRepository userRepository;
    private UserMapper userMapper;

    @Autowired
    public DiagnosisMapper(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public Diagnosis toEntity(DiagnosisRequest request) {
        Diagnosis entity = mapFields(request, new Diagnosis());
        if (request.getUserId() != null) {
            User user = userRepository.findById(request.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found with ID: " + request.getUserId()));
            entity.setUser(user);
        }

        if (request.getUserDocId() != null) {
            User userDoc = userRepository.findById(request.getUserDocId())
                    .orElseThrow(() -> new RuntimeException("User not found with ID: " + request.getUserDocId()));
            entity.setUserDoc(userDoc);
        }
        return entity;
    }

    @Override
    public DiagnosisResponse toResponse(Diagnosis entity) {
        DiagnosisResponse response = mapFields(entity, new DiagnosisResponse());
        if (entity.getUser() != null) {
            response.setUser(userMapper.toResponse(entity.getUser()));
        }
        if (entity.getUserDoc() != null) {
            response.setUserDoc(userMapper.toResponse(entity.getUserDoc()));
        }
        return response;
    }
}
