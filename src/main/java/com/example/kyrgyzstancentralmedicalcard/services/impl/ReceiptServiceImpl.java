package com.example.kyrgyzstancentralmedicalcard.services.impl;

import com.example.kyrgyzstancentralmedicalcard.entity.Receipt;
import com.example.kyrgyzstancentralmedicalcard.entity.User;
import com.example.kyrgyzstancentralmedicalcard.repository.ReceiptRepository;
import com.example.kyrgyzstancentralmedicalcard.repository.UserRepository;
import com.example.kyrgyzstancentralmedicalcard.services.AuthService;
import com.example.kyrgyzstancentralmedicalcard.services.ReceiptService;
import com.example.kyrgyzstancentralmedicalcard.utils.RandomNumberGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReceiptServiceImpl implements ReceiptService {
    private final ReceiptRepository receiptRepository;
    private final UserRepository userRepository;
    private final AuthService authService;

    @Override
    public Receipt createReceipt(Receipt receipt, Long userId) {
        User currentDoc = authService.getCurrentUser();
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Пользователь не найден"));

        receipt.setDoctor(currentDoc);
        receipt.setUser(user);

        String randomNumber;
        do {
            Integer randomNum = RandomNumberGenerator.generateRandomNumber();
            randomNumber = String.valueOf(randomNum);
        }while(receiptRepository.existsByNumber(randomNumber));
        receipt.setNumber(randomNumber);

        return receiptRepository.save(receipt);
    }

    @Override
    public Receipt getById(Long id) {
        return receiptRepository.findById(id).orElseThrow(() -> new RuntimeException("Рецепт не найден"));
    }

    @Override
    public List<Receipt> getAll() {
        User currentUser = authService.getCurrentUser();
        return currentUser.getReceipts();
    }

    @Override
    public List<Receipt> getAllByName(String name) {
        return receiptRepository.findByName(name).orElseThrow(() -> new RuntimeException("Рецент с таким названием не найден"));
    }

    @Override
    public List<Receipt> getAllByDate(LocalDate date) {
        User currentUser = authService.getCurrentUser();
        return receiptRepository.findByDateCreatedAndUser(date, currentUser).orElseThrow(() -> new RuntimeException(""));
    }

    @Override
    public List<Receipt> getAllByUserId(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Пользователь не найден"));
        return receiptRepository.findAllByUser(user);
    }
}
