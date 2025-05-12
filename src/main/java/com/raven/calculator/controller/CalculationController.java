package com.raven.calculator.controller;

import com.raven.calculator.dto.CalculationRequest;
import com.raven.calculator.dto.CalculationResponse;
import com.raven.calculator.model.CalculationHistory;
import com.raven.calculator.service.CalculationHistoryService;
import com.raven.calculator.service.CalculationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Calculator", description = "Perform arithmetic operations")
@RestController
@RequestMapping("/api")
@SecurityRequirement(name = "BearerAuth")
public class CalculationController {

    private static final Logger logger = LoggerFactory.getLogger(CalculationController.class);

    @Autowired
    private CalculationService calculationService;

    @Autowired
    private CalculationHistoryService calculationHistoryService;

    @Operation(summary = "Perform a calculation")
    @PostMapping("/calculate")
    public CalculationResponse calculate(@Valid @RequestBody CalculationRequest request) {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        logger.info("User ID: {}", userId);
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
}