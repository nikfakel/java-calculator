package com.raven.calculator.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.UUID;

@Data
@Entity
public class CalculationHistory {

    @Id
    @GeneratedValue
    private UUID id;

    @Enumerated(EnumType.STRING)
    private Operation operation;

    private double operandA;

    private double operandB;

    private BigDecimal result;

    private ZonedDateTime timestamp;

    private String userId;

    public enum Operation {
        ADDITION, SUBTRACTION, MULTIPLICATION, DIVISION, POWER, SQUARE_ROOT
    }
}