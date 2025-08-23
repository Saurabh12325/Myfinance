package com.myfinance.Myfinance.Repository;

import com.myfinance.Myfinance.Entity.ExpenseEntity;
import com.myfinance.Myfinance.Entity.IncomeEntity;
import com.myfinance.Myfinance.dto.IncomeDto;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface IncomeRepository extends JpaRepository<IncomeEntity, Long> {
    List<IncomeEntity> findByProfileIdOrderByDateDesc(Long profileId);

    // Select * from expense where profile_id = ? order by date desc limit 5
    List<IncomeEntity> findTop5ByProfileIdOrderByDateDesc(Long profileId);

    @Query("SELECT SUM(e.amount) from IncomeEntity e WHERE e.profile.id = :profileId")
    BigDecimal findTotalIncomeByProfileId(@Param("profileId") Long profileId);
    List<IncomeEntity> findByProfileIdAndDateBetweenAndNameContainingIgnoreCase(Long profileId, LocalDate startDate, LocalDate endDate, String keyword, Sort sort);

    List<IncomeEntity> findByProfileIdAndDateBetween(Long profileId, LocalDate startDate, LocalDate endDate);
}
