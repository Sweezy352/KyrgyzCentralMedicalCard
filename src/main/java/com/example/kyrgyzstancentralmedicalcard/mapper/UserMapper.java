package com.example.kyrgyzstancentralmedicalcard.mapper;

import com.example.kyrgyzstancentralmedicalcard.dto.request.UserRequest;
import com.example.kyrgyzstancentralmedicalcard.dto.response.UserInsurancesResponse;
import com.example.kyrgyzstancentralmedicalcard.dto.response.UserResponse;
import com.example.kyrgyzstancentralmedicalcard.dto.response.UserResponseInsurance;
import com.example.kyrgyzstancentralmedicalcard.dto.view.UserView;
import com.example.kyrgyzstancentralmedicalcard.entity.User;
import com.example.kyrgyzstancentralmedicalcard.entity.UserInsurances;
import com.example.kyrgyzstancentralmedicalcard.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    private final RoleRepository roleRepository;
    private final UserInsurancesMapper userInsurancesMapper;

    @Autowired
    public UserMapper(RoleRepository roleRepository, UserInsurancesMapper userInsurancesMapper) {
        this.roleRepository = roleRepository;
        this.userInsurancesMapper = userInsurancesMapper;
    }

    public User toEntity(UserRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Проблема");
        }
        return User.builder()
                .id(request.getId())
                .inn(request.getInn())
                .fio(request.getFio())
                .password(request.getPassword())
                .gender(request.getGender())
                .birthday(request.getBirthday())
                .en(request.getEn())
                .blood(request.getBlood())
                .rh(request.getRh())
                .enName(request.getEnName())
                .build();
    }

    public UserResponse toResponse(User entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Проблема");
        }
        return UserResponse.builder()
                .id(entity.getId())
                .fio(entity.getFio())
                .inn(entity.getInn())
                .gender(entity.getGender())
                .birthday(entity.getBirthday())
                .en(entity.getEn())
                .blood(entity.getBlood())
                .rh(entity.getRh())
                .enName(entity.getEnName())
                .build();
    }

    public UserResponseInsurance toResponseInsurances(User entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Проблема");
        }
        return UserResponseInsurance.builder()
                .id(entity.getId())
                .fio(entity.getFio())
                .inn(entity.getInn())
                .gender(entity.getGender())
                .birthday(entity.getBirthday())
                .en(entity.getEn())
                .blood(entity.getBlood())
                .rh(entity.getRh())
                .enName(entity.getEnName())
                .insurances(userInsurancesMapper.toResponse(entity.getUserInsurances()))
                .build();
    }
    public UserView toView(User entity){
        if(entity == null){
            throw new IllegalArgumentException("Проблема");
        }
        String roleName = entity.getRoles().get(0).getRoleName();

        return UserView.builder().id(entity.getId()).fio(entity.getFio()).roleName(roleName).build();
    }
}
