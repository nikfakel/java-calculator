package com.raven.calculator.dto;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import lombok.Getter;

@Getter
public class CalculationResponse {

    private final String id;
    private final String operation;
    private final BigDecimal operandA;
    private final BigDecimal operandB;
    private final BigDecimal result;
    private final ZonedDateTime timestamp;
    private final String userId;

    public CalculationResponse(
            String id,
            String operation,
            BigDecimal operandA,
            BigDecimal operandB,
            BigDecimal result,
            ZonedDateTime timestamp,
            String userId) {
        this.id = id;
        this.operation = operation;
        this.operandA = operandA;
        this.operandB = operandB;
        this.result = result;
        this.timestamp = timestamp;
        this.userId = userId;
    }
}
