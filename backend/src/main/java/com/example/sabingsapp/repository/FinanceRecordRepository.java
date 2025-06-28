package com.example.sabingsapp.repository;

import com.example.sabingsapp.model.FinanceRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FinanceRecordRepository extends JpaRepository<FinanceRecord, Long> {
    List<FinanceRecord> findByWorkspaceAndDateBetween(String workspace, LocalDate start, LocalDate end);
    List<FinanceRecord> findByWorkspace(String workspace);
}
