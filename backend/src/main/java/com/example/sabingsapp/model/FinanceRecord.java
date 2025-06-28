package com.example.sabingsapp.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "finance_record")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FinanceRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;
    private String type; // income, expense, transfer
    private Double amount;
    private String source;
    private String category;
    private String tag;
    private String targetAccount;
    private String fromAccount;
    private String toAccount;
    private String frequency;
    private Boolean isFixedExpense;
    private String workspace; // personal or business
}
