package com.myfinance.Myfinance.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tbl_income")
public class ExpenseEntity {
   private Long id;
   private String name;
   private String icon;
   private LocalDate date;
   private BigDecimal amount;
   private LocalDateTime createdAt;
   private LocalDateTime updatedAt;
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "profile_id",nullable = false)
   private ProfileEntity profile;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id",nullable = false)
   private CategoryEntity category;
}
