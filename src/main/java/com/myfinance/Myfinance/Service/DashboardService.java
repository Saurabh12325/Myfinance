package com.myfinance.Myfinance.Service;

import com.myfinance.Myfinance.Entity.ProfileEntity;
import com.myfinance.Myfinance.dto.ExpenseDto;
import com.myfinance.Myfinance.dto.IncomeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.IntStream.concat;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final IncomeService incomeService;
    private final ExpenseService expenseService;
    private final ProfileService profileService;

    public Map<String,Object> getDashboardData(){
        ProfileEntity profile = profileService.getCurrentProfile();
        Map<String,Object> returnvalue = new LinkedHashMap<>();
        List<IncomeDto> latestIncome = incomeService.getLatest5ExpensesForCurrentUser();
        List<ExpenseDto> latestExpense = expenseService.getLatest5ExpensesForCurrentUser();
        concat(latestIncome.stream().map(income->
                ReadIncomeDto.builder()

        ))

    }


}
