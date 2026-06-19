package com.example.kyrgyzstancentralmedicalcard.controller;

import com.example.kyrgyzstancentralmedicalcard.dto.request.ReceiptDtoRequest;
import com.example.kyrgyzstancentralmedicalcard.dto.response.ReceiptDtoResponse;
import com.example.kyrgyzstancentralmedicalcard.mapper.ReceiptMapper;
import com.example.kyrgyzstancentralmedicalcard.services.ReceiptService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/receipt")
@RequiredArgsConstructor
public class ReceiptController {
    private final ReceiptService receiptService;
    private final ReceiptMapper receiptMapper;

    @PostMapping("/create-receipt/{id}")
    public ResponseEntity<ReceiptDtoResponse> createReceipt(@PathVariable("id") Long id, @RequestBody ReceiptDtoRequest request){
        return ResponseEntity.ok(receiptMapper.toDto(receiptService.createReceipt(receiptMapper.toEntity(request), id)));
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<ReceiptDtoResponse> getById(@PathVariable("id") Long id){
        return ResponseEntity.ok(receiptMapper.toDto(receiptService.getById(id)));
    }

    @GetMapping("/get-all-receipts")
    public ResponseEntity<List<ReceiptDtoResponse>> getAllReceipts(){
        return ResponseEntity.ok(receiptService.getAll().stream().map(receiptMapper::toDto).toList());
    }

    @GetMapping("/get-all-by-user/{id}") // Добавленный эндпоинт
    public ResponseEntity<List<ReceiptDtoResponse>> getAllReceiptsByUserId(@PathVariable Long id) {
        return ResponseEntity.ok(receiptService.getAllByUserId(id).stream().map(receiptMapper::toDto).toList());
    }

    @GetMapping("/get-by-name")
    public ResponseEntity<List<ReceiptDtoResponse>> getAllReceiptsByName(@RequestParam String name){
        return ResponseEntity.ok(receiptService.getAllByName(name).stream().map(receiptMapper::toDto).toList());
    }

    @GetMapping("/get-by-date")
    public ResponseEntity<List<ReceiptDtoResponse>> getAllReceiptsByDate(@RequestParam LocalDate date){
        return ResponseEntity.ok(receiptService.getAllByDate(date).stream().map(receiptMapper::toDto).toList());
    }
}
