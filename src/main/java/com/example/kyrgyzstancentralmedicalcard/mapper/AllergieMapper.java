package com.example.kyrgyzstancentralmedicalcard.mapper;

import com.example.kyrgyzstancentralmedicalcard.dto.request.AllergieDtoRequest;
import com.example.kyrgyzstancentralmedicalcard.dto.request.UserRequest;
import com.example.kyrgyzstancentralmedicalcard.dto.response.AllergieDtoResponse;
import com.example.kyrgyzstancentralmedicalcard.entity.AllergieEntity;
import com.example.kyrgyzstancentralmedicalcard.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AllergieMapper {
    private final UserMapper userMapper;

    public AllergieEntity toEntity(AllergieDtoRequest request){
        if(request == null){
            throw new IllegalArgumentException("Проблема");
        }

        return AllergieEntity.builder().name(request.getName()).description(request.getDescription()).build();
    }

    public AllergieDtoResponse toDtoResponse(AllergieEntity entity){
        if(entity == null) {
            throw new IllegalArgumentException("Проблема");
        }

        return AllergieDtoResponse
                .builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .userViewDoc(userMapper.toView(entity.getUser()))
                .dateCreated(entity.getDateCreated())
                .dateUpdated(entity.getDateUpdated())
                .status(entity.getStatus())
                .build();
    }
}
