package com.raven.calculator.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class CalculationRequest {
    @NotNull
    private Operation operation;

    @NotNull
    @DecimalMin(value = "-1000000")
    @DecimalMax(value = "1000000")
    private BigDecimal operandA;

    @DecimalMin(value = "-1000000")
    @DecimalMax(value = "1000000")
    private BigDecimal operandB;

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public void setOperandA(BigDecimal operandA) {
        this.operandA = operandA;
    }

    public void setOperandB(BigDecimal operandB) {
        this.operandB = operandB;
    }

    public enum Operation {
        ADDITION, SUBTRACTION, MULTIPLICATION, DIVISION, POWER, SQUARE_ROOT
    }
}