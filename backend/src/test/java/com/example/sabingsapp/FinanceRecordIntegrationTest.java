package com.example.sabingsapp;

import com.example.sabingsapp.model.FinanceRecord;
import com.example.sabingsapp.repository.FinanceRecordRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Testcontainers
class FinanceRecordIntegrationTest {
    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16")
            .withDatabaseName("testdb")
            .withUsername("testuser")
            .withPassword("testpass");

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @Autowired
    private FinanceRecordRepository repository;

    @Test
    void testSaveAndFindByWorkspace() {
        FinanceRecord record = FinanceRecord.builder()
                .date(LocalDate.now())
                .type("expense")
                .amount(200.0)
                .workspace("business")
                .build();
        repository.save(record);
        List<FinanceRecord> found = repository.findByWorkspace("business");
        assertThat(found).isNotEmpty();
        assertThat(found.get(0).getType()).isEqualTo("expense");
    }
}
