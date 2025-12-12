package com.example.kyrgyzstancentralmedicalcard.mapper;

import com.example.kyrgyzstancentralmedicalcard.dto.request.ProductRequest;
import com.example.kyrgyzstancentralmedicalcard.dto.request.ReceiptDtoRequest;
import com.example.kyrgyzstancentralmedicalcard.dto.response.ProductResponse;
import com.example.kyrgyzstancentralmedicalcard.dto.response.ReceiptDtoResponse;
import com.example.kyrgyzstancentralmedicalcard.entity.Product;
import com.example.kyrgyzstancentralmedicalcard.entity.Receipt;
import com.example.kyrgyzstancentralmedicalcard.repository.ReceiptRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReceiptMapper {
    private final ReceiptRepository receiptRepository;
    private final UserMapper userMapper;

    public Receipt toEntity(ReceiptDtoRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Проблема");
        }

        return Receipt.builder().name(request.getName()).description(request.getDescription()).build();
    }

    public ReceiptDtoResponse toDto(Receipt entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Проблема");
        }
        return ReceiptDtoResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .userViewDoc(userMapper.toView(entity.getDoctor()))
                .dateCreated(entity.getDateCreated())
                .build();
    }
}
