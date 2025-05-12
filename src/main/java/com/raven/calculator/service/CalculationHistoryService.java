package com.raven.calculator.service;

import com.raven.calculator.model.CalculationHistory;
import com.raven.calculator.repository.CalculationHistoryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;


@Service
public class CalculationHistoryService {

    @Autowired
    private CalculationHistoryRepository historyRepository;

    public void saveCalculation(String userId, CalculationHistory.Operation operation, double operandA, double operandB, BigDecimal result) {
        CalculationHistory history = new CalculationHistory();
        history.setUserId(userId);
        history.setOperation(operation);
        history.setOperandA(operandA);
        history.setOperandB(operandB);
        history.setResult(result);
        history.setTimestamp(ZonedDateTime.now());
        historyRepository.save(history);
    }

    public List<CalculationHistory> getHistory(String userId, CalculationHistory.Operation operation,
                                               ZonedDateTime startDate, ZonedDateTime endDate,
                                               int page, int size, String sortDirection) {
        Sort sort = Sort.by(sortDirection.equalsIgnoreCase("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC, "timestamp");
        PageRequest pageRequest = PageRequest.of(page, size, sort);
        return historyRepository.findByFilters(userId, operation, startDate, endDate, pageRequest).getContent();
    }

    public CalculationHistory getHistoryById(UUID id) {
        return historyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("History item not found with ID: " + id));
    }

    public void deleteHistoryById(UUID id) {
        if (!historyRepository.existsById(id)) {
            throw new EntityNotFoundException("History item not found with ID: " + id);
        }
        historyRepository.deleteById(id);
    }
}