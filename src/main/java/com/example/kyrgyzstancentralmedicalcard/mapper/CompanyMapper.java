package com.example.kyrgyzstancentralmedicalcard.mapper;

import com.example.kyrgyzstancentralmedicalcard.dto.request.CompanyRequest;
import com.example.kyrgyzstancentralmedicalcard.dto.response.CompanyResponse;
import com.example.kyrgyzstancentralmedicalcard.dto.response.UserResponse;
import com.example.kyrgyzstancentralmedicalcard.entity.Company;
import com.example.kyrgyzstancentralmedicalcard.entity.User;
import com.example.kyrgyzstancentralmedicalcard.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class CompanyMapper extends BaseMapperImpl<Company, CompanyRequest, CompanyResponse> {

    private UserRepository userRepository;
    private UserMapper userMapper;

    @Autowired
    public CompanyMapper(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public Company toEntity(CompanyRequest request) {
        Company entity = mapFields(request, new Company());
        if (request.getUserId() != null) {
            User user = userRepository.findById(request.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found with ID: " + request.getUserId()));
            entity.setUser(user);
        }
        return entity;
    }

    @Override
    public CompanyResponse toResponse(Company entity) {
        CompanyResponse response = mapFields(entity, new CompanyResponse());
        if (entity.getUser() != null) {
            UserResponse userResponse = userMapper.toResponse(entity.getUser());
            response.setUser(userResponse);
        }
        return response;
    }
}
