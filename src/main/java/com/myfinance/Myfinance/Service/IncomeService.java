package com.myfinance.Myfinance.Service;

import com.myfinance.Myfinance.Entity.CategoryEntity;
import com.myfinance.Myfinance.Entity.IncomeEntity;
import com.myfinance.Myfinance.Entity.ProfileEntity;
import com.myfinance.Myfinance.Mapper.Mapper;
import com.myfinance.Myfinance.Repository.CategoryRepository;
import com.myfinance.Myfinance.Repository.IncomeRepository;

import com.myfinance.Myfinance.dto.IncomeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IncomeService {
    private final ProfileService profileService;
    private final CategoryRepository categoryRepository;
    private final IncomeRepository incomeRepository;

    public IncomeDto addIncome(IncomeDto dto){
        ProfileEntity profile = profileService.getCurrentProfile();
        CategoryEntity category = categoryRepository.findById(dto.getCategoryId()).orElseThrow(() -> new RuntimeException("Category not found"));
        IncomeEntity newExpense = Mapper.mapToIncomeEntity(dto,profile,category);
        newExpense = incomeRepository.save(newExpense) ;
        return Mapper.mapToIncomeDto(newExpense);
    }
}
