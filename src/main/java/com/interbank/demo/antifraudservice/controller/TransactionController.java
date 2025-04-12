package com.interbank.demo.antifraudservice.controller;

import com.interbank.demo.antifraudservice.dto.TransactionRequest;
import com.interbank.demo.antifraudservice.dto.TransactionResponse;
import com.interbank.demo.antifraudservice.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService service;

    @PostMapping
    public ResponseEntity<TransactionResponse> create(@RequestBody TransactionRequest request) {
        return ResponseEntity.ok(service.createTransaction(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionResponse> get(@PathVariable UUID id) {
        return ResponseEntity.ok(service.getById(id));
    }
}
