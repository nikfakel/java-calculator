package com.raven.calculator.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.UUID;
import lombok.Data;

@Data
@Entity
public class CalculationHistory {
    @Id
    @Column(name = "id", nullable = false)
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
        ADDITION,
        SUBTRACTION,
        MULTIPLICATION,
        DIVISION,
        POWER,
        SQUARE_ROOT
    }
}
