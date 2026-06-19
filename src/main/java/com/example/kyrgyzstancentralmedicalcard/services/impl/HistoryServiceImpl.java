package com.example.kyrgyzstancentralmedicalcard.services.impl;

import com.example.kyrgyzstancentralmedicalcard.entity.History;
import com.example.kyrgyzstancentralmedicalcard.entity.User;
import com.example.kyrgyzstancentralmedicalcard.repository.HistoryRepository;
import com.example.kyrgyzstancentralmedicalcard.repository.UserRepository;
import com.example.kyrgyzstancentralmedicalcard.services.AuthService;
import com.example.kyrgyzstancentralmedicalcard.services.HistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HistoryServiceImpl implements HistoryService {
    private final HistoryRepository historyRepository;
    private final UserRepository userRepository;
    private final AuthService authService;

    @Override
    public List<History> getAllHistoriesByUserId(Long userId) {
        return historyRepository.findAllByUserId(userId).orElseThrow(() -> new RuntimeException("Такой истории не существует"));
    }

    @Override
    public History createHistory(History history, Long userId) {
        User userDoc = authService.getCurrentUser();
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Пользователь не найде"));
        history.setUserDoc(userDoc);
        history.setUser(user);
        return historyRepository.save(history);
    }

    @Override
    public History getById(Long id) {
        return historyRepository.findById(id).orElseThrow(() -> new RuntimeException("Такой истории не существует"));
    }

    @Override
    public List<History> getByDate(LocalDate date) {
        return historyRepository.findByDateCreated(date).orElseThrow(() -> new RuntimeException("Такой истории не существует"));
    }

    @Override
    public List<History> getByName(String name) {
        return historyRepository.findByName(name).orElseThrow(() -> new RuntimeException("Такая история не найдена"));
    }

    @Override
    public List<History> getByDocFIO(String docFIO) {
        User user = authService.getCurrentUser();
        return user.getHistories().stream().filter(history -> history.getUserDoc().getFio().equals(docFIO)).toList();
    }
}
