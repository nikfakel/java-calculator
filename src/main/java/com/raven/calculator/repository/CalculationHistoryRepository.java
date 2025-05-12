package com.raven.calculator.repository;

import com.raven.calculator.model.CalculationHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalculationHistoryRepository extends JpaRepository<CalculationHistory, Long> {
}