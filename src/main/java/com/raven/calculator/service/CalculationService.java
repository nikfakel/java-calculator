package com.raven.calculator.service;

import com.raven.calculator.dto.CalculationRequest;
import com.raven.calculator.dto.CalculationResponse;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.ZonedDateTime;
import java.util.UUID;

@Service
public class CalculationService {

    public CalculationResponse calculate(CalculationRequest request, String userId) {
        BigDecimal result;
        switch (request.getOperation()) {
            case ADDITION:
                result = request.getOperandA().add(request.getOperandB());
                break;
            case SUBTRACTION:
                result = request.getOperandA().subtract(request.getOperandB());
                break;
            case MULTIPLICATION:
                result = request.getOperandA().multiply(request.getOperandB());
                break;
            case DIVISION:
                if (request.getOperandB().compareTo(BigDecimal.ZERO) == 0) {
                    throw new IllegalArgumentException("Division by zero is not allowed.");
                }
                result = request.getOperandA().divide(request.getOperandB(), 10, RoundingMode.HALF_UP);
                break;
            case POWER:
                result = request.getOperandA().pow(request.getOperandB().intValue());
                break;
            case SQUARE_ROOT:
                if (request.getOperandA().compareTo(BigDecimal.ZERO) < 0) {
                    throw new IllegalArgumentException("Square root of negative numbers is not allowed.");
                }
                result = BigDecimal.valueOf(Math.sqrt(request.getOperandA().doubleValue()));
                break;
            default:
                throw new IllegalArgumentException("Invalid operation.");
        }

        return new CalculationResponse(
                UUID.randomUUID().toString(),
                request.getOperation().name(),
                request.getOperandA(),
                request.getOperandB(),
                result,
                ZonedDateTime.now(),
                userId
        );
    }
}