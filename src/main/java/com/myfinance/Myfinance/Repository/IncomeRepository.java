package com.myfinance.Myfinance.Repository;

import com.myfinance.Myfinance.Entity.IncomeEntity;
import com.myfinance.Myfinance.dto.IncomeDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncomeRepository extends JpaRepository<IncomeEntity, Long> {
}
