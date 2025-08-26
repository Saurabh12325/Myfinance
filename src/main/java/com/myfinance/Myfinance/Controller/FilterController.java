package com.myfinance.Myfinance.Controller;

import com.myfinance.Myfinance.Service.ExpenseService;
import com.myfinance.Myfinance.Service.IncomeService;
import com.myfinance.Myfinance.dto.FilterDto;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/filter")
@RequiredArgsConstructor
public class FilterController {
private final ExpenseService expenseService;
private final IncomeService incomeService;

@PostMapping("/filter")
    public ResponseEntity<?> filterTransaction(@RequestBody FilterDto filter){
    LocalDate startDate = filter.getStartDate() != null ? filter.getStartDate() : LocalDate.MIN;
    LocalDate endDate = filter.getEndDate() != null ? filter.getEndDate() : LocalDate.now();
    String keyword = filter.getKeyword() != null ? filter.getKeyword() : "";
    String sortField = filter.getSortField() != null ? filter.getSortField() : "date";
     Sort.Direction direction = "desc".equalsIgnoreCase(filter.getSortOrder()) ? Sort.Direction.DESC : Sort.Direction.ASC;
    }



}
