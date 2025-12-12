package com.example.kyrgyzstancentralmedicalcard.services.impl;

import com.example.kyrgyzstancentralmedicalcard.entity.AllergieEntity;
import com.example.kyrgyzstancentralmedicalcard.entity.User;
import com.example.kyrgyzstancentralmedicalcard.repository.AllergieRepository;
import com.example.kyrgyzstancentralmedicalcard.repository.UserRepository;
import com.example.kyrgyzstancentralmedicalcard.services.AllergieService;
import com.example.kyrgyzstancentralmedicalcard.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AllergieServiceImpl implements AllergieService {
    private final AllergieRepository allergieRepository;
    private final UserRepository userRepository;
    private final AuthService authService;

    @Override
    public AllergieEntity addAllergie(Long userId, AllergieEntity allergieEntity) {
        User doctor = authService.getCurrentUser();
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Пользователь не найден"));
        allergieEntity.setUser(user);
        allergieEntity.setDoctor(doctor);
        return allergieRepository.save(allergieEntity);
    }

    @Override
    public AllergieEntity getAllergieById(Long id) {
        return allergieRepository.findById(id).orElseThrow(() -> new RuntimeException("Алергия не найдена"));
    }

    @Override
    public List<AllergieEntity> getAll() {
        User currentUser = authService.getCurrentUser();
        return currentUser.getAllergieEntities();
    }

    @Override
    public List<AllergieEntity> getAllByName(String name) {
        User currentUser = authService.getCurrentUser();
        return allergieRepository.findByNameAndUser(name, currentUser).orElseThrow(() -> new RuntimeException("Алергия не найдена"));
    }

    @Override
    public List<AllergieEntity> getAllByDate(LocalDate date) {
        User currentUser = authService.getCurrentUser();
        return allergieRepository.findByDateCreatedAndUser(date, currentUser).orElseThrow(() -> new RuntimeException("Алергия не найдена"));
    }

    @Override
    public List<AllergieEntity> getAllByDoctor(String doctorName) {
        User currentUser = authService.getCurrentUser();
        User doctor = userRepository.findByFio(doctorName).orElseThrow(() -> new RuntimeException("Пользователь не найден"));
        return allergieRepository.findByDoctorAndUser(doctor, currentUser).orElseThrow(() -> new RuntimeException("Алергия не найдена"));
    }
}
