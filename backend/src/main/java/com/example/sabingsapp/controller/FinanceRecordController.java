package com.example.sabingsapp.controller;

import com.example.sabingsapp.model.FinanceRecord;
import com.example.sabingsapp.service.FinanceRecordService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/records")
@Tag(name = "Finance Records", description = "CRUD operations for finance records")
public class FinanceRecordController {
    private static final Logger logger = LoggerFactory.getLogger(FinanceRecordController.class);
    private final FinanceRecordService service;

    public FinanceRecordController(FinanceRecordService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Get all records for a workspace")
    @CircuitBreaker(name = "financeRecords")
    public List<FinanceRecord> getAll(@RequestParam String workspace) {
        logger.info("Fetching all records for workspace: {}", workspace);
        return service.getAll(workspace);
    }

    @GetMapping("/range")
    @Operation(summary = "Get records by date range for a workspace")
    @CircuitBreaker(name = "financeRecords")
    public List<FinanceRecord> getByDateRange(@RequestParam String workspace,
                                              @RequestParam String start,
                                              @RequestParam String end) {
        logger.info("Fetching records for workspace: {} from {} to {}", workspace, start, end);
        return service.getByDateRange(workspace, LocalDate.parse(start), LocalDate.parse(end));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a record by ID")
    @CircuitBreaker(name = "financeRecords")
    public ResponseEntity<FinanceRecord> getById(@PathVariable Long id) {
        logger.info("Fetching record by id: {}", id);
        Optional<FinanceRecord> record = service.getById(id);
        return record.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Create a new record")
    @CircuitBreaker(name = "financeRecords")
    public FinanceRecord create(@RequestBody FinanceRecord record) {
        logger.info("Creating record: {}", record);
        return service.save(record);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a record by ID")
    @CircuitBreaker(name = "financeRecords")
    public ResponseEntity<FinanceRecord> update(@PathVariable Long id, @RequestBody FinanceRecord record) {
        logger.info("Updating record id {}: {}", id, record);
        return service.getById(id)
                .map(existing -> {
                    record.setId(id);
                    return ResponseEntity.ok(service.save(record));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a record by ID")
    @CircuitBreaker(name = "financeRecords")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        logger.info("Deleting record id {}", id);
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
