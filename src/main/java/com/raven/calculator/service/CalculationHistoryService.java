package com.raven.calculator.service;

import com.raven.calculator.controller.CalculationController;
import com.raven.calculator.model.CalculationHistory;
import com.raven.calculator.repository.CalculationHistoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.ZonedDateTime;


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
}