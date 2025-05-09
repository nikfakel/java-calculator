package com.raven.calculator.controller;

import com.raven.calculator.dto.CalculationRequest;
import com.raven.calculator.dto.CalculationResponse;
import com.raven.calculator.service.CalculationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CalculationController {

    @Autowired
    private CalculationService calculationService;

    @PostMapping("/calculate")
    public CalculationResponse calculate(@Valid @RequestBody CalculationRequest request) {
        String userId = "user-id"; // Replace with actual user ID logic
        return calculationService.calculate(request, userId);
    }
}