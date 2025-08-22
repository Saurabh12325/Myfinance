package com.myfinance.Myfinance.Repository;

import com.myfinance.Myfinance.Entity.ExpenseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<ExpenseEntity,Long> {
}
