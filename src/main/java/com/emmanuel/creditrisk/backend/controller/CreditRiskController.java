package com.emmanuel.creditrisk.backend.controller;

import com.emmanuel.creditrisk.backend.dto.CreditRiskRequest;
import com.emmanuel.creditrisk.backend.dto.CreditRiskResponse;
import com.emmanuel.creditrisk.backend.service.CreditRiskService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/credit-risk")
public class CreditRiskController {

    private final CreditRiskService creditRiskService;

    public CreditRiskController(CreditRiskService creditRiskService) {
        this.creditRiskService = creditRiskService;
    }

    // Browser-friendly (prevents Whitelabel / 405 confusion)
    @GetMapping
    public ResponseEntity<?> info() {
        return ResponseEntity.ok(
                "Credit Risk API is running. POST /api/credit-risk/score with JSON body to score."
        );
    }

    @PostMapping("/score")
    public ResponseEntity<CreditRiskResponse> score(@Valid @RequestBody CreditRiskRequest request) {
        return ResponseEntity.ok(creditRiskService.score(request));
    }
}