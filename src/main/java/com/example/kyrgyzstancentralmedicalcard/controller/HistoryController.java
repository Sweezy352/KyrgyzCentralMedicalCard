package com.example.kyrgyzstancentralmedicalcard.controller;

import com.example.kyrgyzstancentralmedicalcard.dto.request.HistoryRequest;
import com.example.kyrgyzstancentralmedicalcard.dto.response.HistoryResponse;
import com.example.kyrgyzstancentralmedicalcard.mapper.HistoryMapper;
import com.example.kyrgyzstancentralmedicalcard.services.HistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/history")
@RequiredArgsConstructor
public class HistoryController {
    private final HistoryService historyService;
    private final HistoryMapper historyMapper;

    @PostMapping("/create-history/{id}")
    public ResponseEntity<HistoryResponse> createHistory(@PathVariable("id") Long userId, @RequestBody HistoryRequest historyRequest){
        return ResponseEntity.ok(historyMapper.toDto(historyService.createHistory(historyMapper.toEntity(historyRequest), userId)));
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<HistoryResponse> getHistoryById(@PathVariable("id") Long id){
        return ResponseEntity.ok(historyMapper.toDto(historyService.getById(id)));
    }

    @GetMapping("/get-all-by-user/{id}")
    public ResponseEntity<List<HistoryResponse>> getAllHistoriesByUserId(@PathVariable("id") Long userId) {
        return ResponseEntity.ok(historyService.getAllHistoriesByUserId(userId).stream().map(historyMapper::toDto).toList());
    }

    @GetMapping("/get-all-by-date")
    public ResponseEntity<List<HistoryResponse>> getAllHistoriesByDate(@RequestParam LocalDate date){
        return ResponseEntity.ok(historyService.getByDate(date).stream().map(historyMapper::toDto).toList());
    }

    @GetMapping("/get-by-name")
    public ResponseEntity<List<HistoryResponse>> getHistoriesByName(@RequestParam String name){
        return ResponseEntity.ok(historyService.getByName(name).stream().map(historyMapper::toDto).toList());
    }

    @GetMapping("/get-by-doc-fio")
    public ResponseEntity<List<HistoryResponse>> getHistoriesByHistory(@RequestParam String docFio){
        return ResponseEntity.ok(historyService.getByDocFIO(docFio).stream().map(historyMapper::toDto).toList());
    }
}
