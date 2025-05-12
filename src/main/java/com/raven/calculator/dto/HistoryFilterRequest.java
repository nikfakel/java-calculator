package com.raven.calculator.dto;

import com.raven.calculator.model.CalculationHistory;
import java.time.ZonedDateTime;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Setter
@Getter
public class HistoryFilterRequest {
    private CalculationHistory.Operation operation;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime startDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime endDate;

    private int page = 0;
    private int size = 10;
    private String sortDirection = "ASC";
}
