package com.myfinance.Myfinance.Controller;

import com.myfinance.Myfinance.Service.IncomeService;
import com.myfinance.Myfinance.dto.ExpenseDto;
import com.myfinance.Myfinance.dto.IncomeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/income")
public class IncomeController {

    private final IncomeService incomeService;

    @PostMapping("/add")
    public ResponseEntity<IncomeDto> addExpense(@RequestBody IncomeDto dto){
        IncomeDto savedExpense = incomeService.addIncome(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedExpense);
    }
}
