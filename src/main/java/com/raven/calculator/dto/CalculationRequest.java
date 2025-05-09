package com.raven.calculator.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public class CalculationRequest {

    @NotNull
    private Operation operation;

    @NotNull
    @DecimalMin(value = "-1000000", inclusive = true)
    @DecimalMax(value = "1000000", inclusive = true)
    private BigDecimal operandA;

    @DecimalMin(value = "-1000000", inclusive = true)
    @DecimalMax(value = "1000000", inclusive = true)
    private BigDecimal operandB;

    // Getters and Setters
    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public BigDecimal getOperandA() {
        return operandA;
    }

    public void setOperandA(BigDecimal operandA) {
        this.operandA = operandA;
    }

    public BigDecimal getOperandB() {
        return operandB;
    }

    public void setOperandB(BigDecimal operandB) {
        this.operandB = operandB;
    }

    public enum Operation {
        ADDITION, SUBTRACTION, MULTIPLICATION, DIVISION, POWER, SQUARE_ROOT
    }
}