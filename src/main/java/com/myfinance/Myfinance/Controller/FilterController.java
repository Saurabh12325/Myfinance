package com.myfinance.Myfinance.Controller;

import com.myfinance.Myfinance.Service.ExpenseService;
import com.myfinance.Myfinance.Service.IncomeService;
import com.myfinance.Myfinance.dto.ExpenseDto;
import com.myfinance.Myfinance.dto.FilterDto;
import com.myfinance.Myfinance.dto.IncomeDto;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class FilterController {
    private final ExpenseService expenseService;
    private final IncomeService incomeService;

    @PostMapping("/filter")
    public ResponseEntity<?> filterTransaction(@RequestBody FilterDto filter) {
        LocalDate startDate = filter.getStartDate() != null ? filter.getStartDate() : LocalDate.MIN;
        LocalDate endDate = filter.getEndDate() != null ? filter.getEndDate() : LocalDate.now();
        String keyword = filter.getKeyword() != null ? filter.getKeyword() : "";
        String sortField = filter.getSortField() != null ? filter.getSortField() : "date";
        Sort.Direction direction = "desc".equalsIgnoreCase(filter.getSortOrder()) ? Sort.Direction.DESC : Sort.Direction.ASC;
        Sort sort = Sort.by(direction, sortField);
        if("income".equals(filter.getType())){
            List<IncomeDto> incomeList = incomeService.filterExpenses(startDate, endDate, keyword, sort);
            return ResponseEntity.ok(incomeList);
        }
        else if ("expense" .equals(filter.getType())){
            List<ExpenseDto> expenseList = expenseService.filterExpenses(startDate, endDate, keyword, sort);
            return ResponseEntity.ok(expenseList);
        }
        else {
            return ResponseEntity.badRequest().body("Invalid type, Invalid type must be income or expense");
        }
    }
}




