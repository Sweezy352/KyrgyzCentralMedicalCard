package com.example.kyrgyzstancentralmedicalcard.services.impl;

import com.example.kyrgyzstancentralmedicalcard.entity.MedicineInsurance;
import com.example.kyrgyzstancentralmedicalcard.entity.User;
import com.example.kyrgyzstancentralmedicalcard.entity.UserInsurances;
import com.example.kyrgyzstancentralmedicalcard.repository.UserInsurancesRepository;
import com.example.kyrgyzstancentralmedicalcard.services.AuthService;
import com.example.kyrgyzstancentralmedicalcard.services.MedicineInsuranceService;
import com.example.kyrgyzstancentralmedicalcard.services.UserInsuranceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserInsuranceServiceImpl implements UserInsuranceService {
    private final UserInsurancesRepository userInsurancesRepository;
    private final MedicineInsuranceService medicineInsuranceService;
    private final AuthService authService;

    @Override
    public UserInsurances addInsuranceToUser(Long medicineInsuranceId) {
        User currentUser = authService.getCurrentUser();
        MedicineInsurance medicineInsurance = medicineInsuranceService.getById(medicineInsuranceId);
        UserInsurances userInsurances = new UserInsurances();
        LocalDate activeDate = LocalDate.now();
        userInsurances.setMedicineInsurance(medicineInsurance);
        if(!userInsurancesRepository.existsByUserId(currentUser.getId())){
            userInsurances.setUser(currentUser);
            userInsurances.setDateActive(activeDate);
            userInsurances.setDateExpire(activeDate.plusMonths(1));
            return userInsurancesRepository.save(userInsurances);
        }
        if(userInsurances.getMedicineInsurance().getInsuranceName().equals("ОМС")) {
            UserInsurances userInsurances1 = userInsurancesRepository.findByUserId(currentUser.getId()).orElseThrow(() -> new RuntimeException("Not found"));
            userInsurances1.setMedicineInsurance(medicineInsuranceService.getById(1L));
            userInsurances.setUser(userInsurances1.getUser());
            userInsurances.setDateActive(activeDate);
            userInsurances.setDateExpire(activeDate.plusMonths(1));
            return userInsurancesRepository.save(userInsurances1);
        }
        if(userInsurances.getMedicineInsurance().getInsuranceName().equals("ДМС")){
            UserInsurances userInsurances1 = userInsurancesRepository.findByUserId(currentUser.getId()).orElseThrow(() -> new RuntimeException("Not found"));
            userInsurances1.setMedicineInsurance(medicineInsuranceService.getById(2L));
            userInsurances.setUser(userInsurances1.getUser());
            userInsurances.setDateActive(activeDate);
            userInsurances.setDateExpire(activeDate.plusMonths(1));
            return userInsurancesRepository.save(userInsurances1);
        }else if(userInsurances.getDateActive().equals(null) && userInsurances.getDateExpire().equals(null)){
            userInsurances.setUser(currentUser);
            userInsurances.setDateActive(activeDate);
            userInsurances.setDateExpire(activeDate.plusMonths(1));
        }
        else {
            throw new RuntimeException("z");
        }
        return null;
    }

    @Override
    public UserInsurances getById(Long id) {
        return userInsurancesRepository.findById(id).orElseThrow(() -> new RuntimeException("Такой страховки не существует"));
    }

    @Override
    public UserInsurances getByUserId() {
        return userInsurancesRepository.findByUserId(authService.getCurrentUser().getId()).orElseThrow(() -> new RuntimeException("Проблема сервира"));
    }
}
