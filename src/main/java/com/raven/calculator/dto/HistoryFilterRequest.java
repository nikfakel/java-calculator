package com.raven.calculator.dto;

import com.raven.calculator.model.CalculationHistory;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.ZonedDateTime;

@Setter
@Getter
public class HistoryFilterRequest {
    private CalculationHistory.Operation operation;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime startDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime endDate;

    private int page = 0; // Default page number
    private int size = 10; // Default page size
    private String sortDirection = "ASC"; // Default sort direction
}