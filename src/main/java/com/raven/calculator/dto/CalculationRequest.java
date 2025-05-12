package com.raven.calculator.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Setter
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

    public enum Operation {
        ADDITION,
        SUBTRACTION,
        MULTIPLICATION,
        DIVISION,
        POWER,
        SQUARE_ROOT
    }
}
