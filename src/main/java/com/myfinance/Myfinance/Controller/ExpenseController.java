package com.myfinance.Myfinance.Controller;

import com.myfinance.Myfinance.Entity.ExpenseEntity;
import com.myfinance.Myfinance.Service.ExpenseService;
import com.myfinance.Myfinance.dto.ExpenseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/expense")
public class ExpenseController {
    private final ExpenseService expenseService;

    public ResponseEntity<ExpenseDto> addExpense(@RequestBody ExpenseDto dto){
       ExpenseDto savedExpense = expenseService.addExpense(dto);
       return ResponseEntity.status(HttpStatus.CREATED).body(savedExpense);
    }
}
