package com.raven.calculator.controller;

import com.raven.calculator.dto.CalculationRequest;
import com.raven.calculator.dto.CalculationResponse;
import com.raven.calculator.dto.HistoryFilterRequest;
import com.raven.calculator.model.CalculationHistory;
import com.raven.calculator.service.CalculationHistoryService;
import com.raven.calculator.service.CalculationService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CalculationController {

    private static final Logger logger = LoggerFactory.getLogger(CalculationController.class);

    @Autowired
    private CalculationService calculationService;

    @Autowired
    private CalculationHistoryService calculationHistoryService;

    @Autowired
    private CalculationHistoryService historyService;

    @PostMapping("/calculate")
    public CalculationResponse calculate(@Valid @RequestBody CalculationRequest request) {
        String userId = extractUserIdFromToken();
        CalculationResponse response = calculationService.calculate(request, userId);
        calculationHistoryService.saveCalculation(
                userId,
                CalculationHistory.Operation.valueOf(request.getOperation().name()),
                request.getOperandA().doubleValue(),
                request.getOperandB() != null ? request.getOperandB().doubleValue() : 0.0,
                response.getResult()
        );
        return response;
    }

    @GetMapping("/history")
    public List<CalculationHistory> getHistory(@ModelAttribute HistoryFilterRequest filterRequest) {
        String userId = extractUserIdFromToken();
        return calculationHistoryService.getHistory(
                userId,
                filterRequest.getOperation(),
                filterRequest.getStartDate(),
                filterRequest.getEndDate(),
                filterRequest.getPage(),
                filterRequest.getSize(),
                filterRequest.getSortDirection()
        );
    }

    @GetMapping("/history/{id}")
    public CalculationHistory getHistoryById(@PathVariable UUID id) {
        String userId = extractUserIdFromToken();
        CalculationHistory history = calculationHistoryService.getHistoryById(id);
        if (!history.getUserId().equals(userId)) {
            throw new SecurityException("Access denied: You are not authorized to view this history item.");
        }
        return history;
    }

    @DeleteMapping("/history/{id}")
    public void deleteHistoryById(@PathVariable UUID id) {
        String userId = extractUserIdFromToken();
        CalculationHistory history = calculationHistoryService.getHistoryById(id);
        if (!history.getUserId().equals(userId)) {
            throw new SecurityException("Access denied: You are not authorized to delete this history item.");
        }
        calculationHistoryService.deleteHistoryById(id);
    }

    private String extractUserIdFromToken() {
        return SecurityContextHolder.getContext().getAuthentication().getName(); // Replace with actual implementation
    }
}