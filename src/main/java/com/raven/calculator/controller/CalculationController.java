package com.raven.calculator.controller;

import com.raven.calculator.dto.CalculationRequest;
import com.raven.calculator.dto.CalculationResponse;
import com.raven.calculator.dto.HistoryFilterRequest;
import com.raven.calculator.model.CalculationHistory;
import com.raven.calculator.service.CalculationHistoryService;
import com.raven.calculator.service.CalculationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Calculator", description = "Perform arithmetic operations")
@RestController
@RequestMapping("/api")
@SecurityRequirement(name = "BearerAuth")
public class CalculationController {
    @Autowired
    private CalculationService calculationService;

    @Autowired
    private CalculationHistoryService calculationHistoryService;

    @Autowired
    private CalculationHistoryService historyService;

    @Operation(summary = "Perform a calculation")
    @PostMapping("/calculate")
    public CalculationResponse calculate(@Valid @RequestBody CalculationRequest request) {
        String userId = extractUserIdFromToken();
        CalculationResponse response = calculationService.calculate(request, userId);
        calculationHistoryService.saveCalculation(
                userId,
                CalculationHistory.Operation.valueOf(request.getOperation().name()),
                request.getOperandA().doubleValue(),
                request.getOperandB() != null ? request.getOperandB().doubleValue() : 0.0,
                response.getResult());
        return response;
    }

    @Operation(summary = "Get calculation history")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "History retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Validation error")
    })
    @GetMapping("/history")
    public List<CalculationHistory> getHistory(
            @Parameter(description = "Operation type to filter history", example = "DIVISION")
            @RequestParam(required = false) CalculationHistory.Operation operation,
            @Parameter(description = "Start date for filtering history", example = "2025-05-01T00:00:00Z")
            @RequestParam(required = false) ZonedDateTime startDate,
            @Parameter(description = "End date for filtering history", example = "2025-06-12T23:59:59Z")
            @RequestParam(required = false) ZonedDateTime endDate,
            @Parameter(description = "Page number for pagination", example = "0")
            @RequestParam(required = false, defaultValue = "0") int page,
            @Parameter(description = "Page size for pagination", example = "5")
            @RequestParam(required = false, defaultValue = "10") int size,
            @Parameter(description = "Sort direction for results", example = "DESC")
            @RequestParam(required = false, defaultValue = "ASC") String sortDirection
    ) {
        String userId = extractUserIdFromToken();
        return calculationHistoryService.getHistory(userId, operation, startDate, endDate, page, size, sortDirection);
    }

    @Operation(summary = "Get calculation history by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "History retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Validation error")
    })
    @GetMapping("/history/{id}")
    public CalculationHistory getHistoryById(@PathVariable UUID id) {
        String userId = extractUserIdFromToken();
        CalculationHistory history = calculationHistoryService.getHistoryById(id);
        if (!history.getUserId().equals(userId)) {
            throw new SecurityException("Access denied: You are not authorized to view this history item.");
        }
        return history;
    }

    @Operation(summary = "Delete calculation history by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "History deleted successfully"),
            @ApiResponse(responseCode = "400", description = "Validation error")
    })
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
