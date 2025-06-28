package com.example.sabingsapp.service;

import com.example.sabingsapp.model.FinanceRecord;
import com.example.sabingsapp.repository.FinanceRecordRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class FinanceRecordService {
    private final FinanceRecordRepository repository;

    public FinanceRecordService(FinanceRecordRepository repository) {
        this.repository = repository;
    }

    @Cacheable(value = "financeRecords", key = "#workspace")
    public List<FinanceRecord> getAll(String workspace) {
        return repository.findByWorkspace(workspace);
    }

    @Cacheable(value = "financeRecords", key = "#workspace + '-' + #start + '-' + #end")
    public List<FinanceRecord> getByDateRange(String workspace, LocalDate start, LocalDate end) {
        return repository.findByWorkspaceAndDateBetween(workspace, start, end);
    }

    public Optional<FinanceRecord> getById(Long id) {
        return repository.findById(id);
    }

    @Caching(evict = {
        @CacheEvict(value = "financeRecords", allEntries = true)
    })
    @Transactional
    public FinanceRecord save(FinanceRecord record) {
        return repository.save(record);
    }

    @Caching(evict = {
        @CacheEvict(value = "financeRecords", allEntries = true)
    })
    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
