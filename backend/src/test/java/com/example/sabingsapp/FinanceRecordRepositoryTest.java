package com.example.sabingsapp;

import com.example.sabingsapp.model.FinanceRecord;
import com.example.sabingsapp.repository.FinanceRecordRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class FinanceRecordRepositoryTest {
    @Autowired
    private FinanceRecordRepository repository;

    @Test
    void testSaveAndFindByWorkspace() {
        FinanceRecord record = FinanceRecord.builder()
                .date(LocalDate.now())
                .type("income")
                .amount(100.0)
                .workspace("personal")
                .build();
        repository.save(record);
        List<FinanceRecord> found = repository.findByWorkspace("personal");
        assertThat(found).isNotEmpty();
        assertThat(found.get(0).getType()).isEqualTo("income");
    }
}
