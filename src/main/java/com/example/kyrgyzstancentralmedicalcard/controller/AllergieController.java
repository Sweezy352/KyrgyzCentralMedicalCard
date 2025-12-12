package com.example.kyrgyzstancentralmedicalcard.controller;

import com.example.kyrgyzstancentralmedicalcard.dto.request.AllergieDtoRequest;
import com.example.kyrgyzstancentralmedicalcard.dto.response.AllergieDtoResponse;
import com.example.kyrgyzstancentralmedicalcard.entity.AllergieEntity;
import com.example.kyrgyzstancentralmedicalcard.mapper.AllergieMapper;
import com.example.kyrgyzstancentralmedicalcard.services.AllergieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/allergies")
@RequiredArgsConstructor
public class AllergieController {
    private final AllergieService allergieService;
    private final AllergieMapper allergieMapper;

    @PostMapping("/add-allergie/{id}")
    public ResponseEntity<AllergieDtoResponse> addAllergie(@PathVariable("id") Long id, AllergieDtoRequest allergieDtoRequest){
        return ResponseEntity.ok(allergieMapper.toDtoResponse(allergieService.addAllergie(id, allergieMapper.toEntity(allergieDtoRequest))));
    }

    @GetMapping("/get-all-allergies/{id}")
    public ResponseEntity<AllergieDtoResponse> getAllergieById(@PathVariable("id") Long id){
        return ResponseEntity.ok(allergieMapper.toDtoResponse(allergieService.getAllergieById(id)));
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<AllergieDtoResponse>> getAll(){
        return ResponseEntity.ok(allergieService.getAll().stream().map(allergieMapper::toDtoResponse).toList());
    }

    @GetMapping("/get-all-by-name")
    public ResponseEntity<List<AllergieDtoResponse>> getAllByName(@RequestParam String name){
        return ResponseEntity.ok(allergieService.getAllByName(name).stream().map(allergieMapper::toDtoResponse).toList());
    }

    @GetMapping("/get-all-by-date")
    public ResponseEntity<List<AllergieDtoResponse>> getAllByDate(@RequestParam LocalDate date){
        return ResponseEntity.ok(allergieService.getAllByDate(date).stream().map(allergieMapper::toDtoResponse).toList());
    }

    @GetMapping("/get-all-by-doctor")
    public ResponseEntity<List<AllergieDtoResponse>> getAllByDoctor(@RequestParam String doctorName){
        return ResponseEntity.ok(allergieService.getAllByDoctor(doctorName).stream().map(allergieMapper::toDtoResponse).toList());
    }
}
