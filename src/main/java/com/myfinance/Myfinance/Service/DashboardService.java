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
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service
@RequiredArgsConstructor
public class DashboardService {

    private final IncomeService incomeService;
    private final ExpenseService expenseService;
    private final ProfileService profileService;

    public Map<String,Object> getDashboardData() {
        ProfileEntity profile = profileService.getCurrentProfile();
        Map<String, Object> returnValue = new LinkedHashMap<>();
        List<IncomeDto> latestIncome = incomeService.getLatest5ExpensesForCurrentUser();
        List<ExpenseDto> latestExpense = expenseService.getLatest5ExpensesForCurrentUser();
        List<RecentTransactionDto> recentTransactionDto =
                Stream.concat(
                                latestIncome.stream().map(income ->
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
                                                .build()
                                ),
                                latestExpense.stream().map(expense ->
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
                                                .build()
                                )
                        )
                        .sorted((a, b) -> {
                            int cmp = b.getDate().compareTo(a.getDate());
                            if (cmp == 0 && a.getCreatedAt() != null && b.getCreatedAt() != null) {
                                return b.getCreatedAt().compareTo(a.getCreatedAt());
                            }
                            return cmp;
                        }).toList();
        returnValue.put("totalBalance" , incomeService.getTotalIncomeForCurrentUser().subtract(expenseService.getTotalExpenseForCurrentUser()));
        returnValue.put("totalExpense",expenseService.getTotalExpenseForCurrentUser());
        returnValue.put(("recent5Expenses"), latestExpense);
        returnValue.put(("recent5Income"), latestIncome);
        returnValue.put("recentTransaction" , recentTransactionDto);
        return returnValue;






    }



}
