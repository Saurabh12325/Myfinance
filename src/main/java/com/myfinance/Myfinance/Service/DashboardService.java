package com.myfinance.Myfinance.Service;

import com.myfinance.Myfinance.Entity.ProfileEntity;
import com.myfinance.Myfinance.dto.ExpenseDto;
import com.myfinance.Myfinance.dto.IncomeDto;
import com.myfinance.Myfinance.dto.RecentTransactionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Stream.concat;


@Service
@RequiredArgsConstructor
public class DashboardService {

    private final IncomeService incomeService;
    private final ExpenseService expenseService;
    private final ProfileService profileService;

    public Map<String,Object> getDashboardData(){
        ProfileEntity profile = profileService.getCurrentProfile();
        Map<String,Object> returnValue = new LinkedHashMap<>();
        List<IncomeDto> latestIncome = incomeService.getLatest5ExpensesForCurrentUser();
        List<ExpenseDto> latestExpense = expenseService.getLatest5ExpensesForCurrentUser();
        concat(latestIncome.stream().map(income->
                RecentTransactionDto.builder()
                        .id(income.getId())
                        .profileId(profile.getId())
                        .name(income.getName())
                        .icon(income.getIcon())
                        .date(income.getDate())
                        .createdAt(income.getCreatedAt())
                        .updatedAt(income.getUpdatedAt())
                        .amount(income.getAmount())
                        .type("income")
                        .build()),
                     latestExpense.stream().map(expense->
                             RecentTransactionDto.builder()
                             .id(expense.getId())
                             .profileId(profile.getId())
                             .name(expense.getName())
                             .icon(expense.getIcon())
                             .date(expense.getDate())
                             .createdAt(expense.getCreatedAt())
                             .updatedAt(expense.getUpdatedAt())
                             .amount(expense.getAmount())
                             .type("expense")
                             .build())
                )




        ))

    }


}
