package com.raven.calculator.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.ZonedDateTime;
import java.util.UUID;

import com.raven.calculator.model.CalculationHistory;

public interface CalculationHistoryRepository extends JpaRepository<CalculationHistory, UUID> {

    @Query("SELECT h FROM CalculationHistory h WHERE h.userId = :userId " +
            "AND (:operation IS NULL OR h.operation = :operation) " +
            "AND (:startDate IS NULL OR h.timestamp >= :startDate) " +
            "AND (:endDate IS NULL OR h.timestamp <= :endDate)")
    Page<CalculationHistory> findByFilters(@Param("userId") String userId,
                                           @Param("operation") CalculationHistory.Operation operation,
                                           @Param("startDate") ZonedDateTime startDate,
                                           @Param("endDate") ZonedDateTime endDate,
                                           Pageable pageable);
}