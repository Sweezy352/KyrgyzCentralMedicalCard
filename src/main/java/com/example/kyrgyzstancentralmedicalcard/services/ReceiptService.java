package com.example.kyrgyzstancentralmedicalcard.services;

import com.example.kyrgyzstancentralmedicalcard.entity.Receipt;

import java.time.LocalDate;
import java.util.List;

public interface ReceiptService {
    Receipt createReceipt(Receipt receipt, Long userId);
    Receipt getById(Long id);
    List<Receipt> getAll();
    List<Receipt> getAllByName(String name);
    List<Receipt> getAllByDate(LocalDate date);
    List<Receipt> getAllByUserId(Long userId); // Добавленный метод
}
