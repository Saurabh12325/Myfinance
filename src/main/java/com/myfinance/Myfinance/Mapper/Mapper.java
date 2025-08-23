package com.myfinance.Myfinance.Mapper;

import com.myfinance.Myfinance.Entity.CategoryEntity;
import com.myfinance.Myfinance.Entity.ExpenseEntity;
import com.myfinance.Myfinance.Entity.IncomeEntity;
import com.myfinance.Myfinance.Entity.ProfileEntity;
import com.myfinance.Myfinance.dto.CategoryDto;
import com.myfinance.Myfinance.dto.ExpenseDto;
import com.myfinance.Myfinance.dto.IncomeDto;
import com.myfinance.Myfinance.dto.ProfileDTO;



public class Mapper {


    public static ProfileEntity mapToEntity(ProfileDTO profileDTO) {
        return ProfileEntity.builder()
                .id(profileDTO.getId())
                            .fullName(profileDTO.getFullName())
                            .email(profileDTO.getEmail())
                        .password(profileDTO.getPassword())
                        .profileImageUrl(profileDTO.getProfileImageUrl())
                        .createdAt(profileDTO.getCreatedAt())
                        .updatedAt(profileDTO.getUpdatedAt())
                .build();

    }
    public static ProfileDTO mapToDTO(ProfileEntity profileEntity) {
        return ProfileDTO.builder()
                .id(profileEntity.getId())
                .fullName(profileEntity.getFullName())
                .email(profileEntity.getEmail())
                .profileImageUrl(profileEntity.getProfileImageUrl())
                .createdAt(profileEntity.getCreatedAt())
                .updatedAt(profileEntity.getUpdatedAt())
                .build();
    }

    public static CategoryEntity mapToCategoryEntity(CategoryDto categoryDto,ProfileEntity profile) {
        return CategoryEntity.builder()
                .name(categoryDto.getName())
                .type(categoryDto.getType())
                .icon(categoryDto.getIcon())
                .profile(profile)
                .build();
    }

    public static  CategoryDto mapToCategoryDto(CategoryEntity categoryEntity) {
        return CategoryDto.builder()
                .id(categoryEntity.getId())
                .name(categoryEntity.getName())
                .type(categoryEntity.getType())
                .icon(categoryEntity.getIcon())
                .profileId(categoryEntity.getProfile() != null ? categoryEntity.getProfile().getId() : null)
                .createdAt(categoryEntity.getCreatedAt())
                .updatedAt(categoryEntity.getUpdatedAt())
                .build();
    }

    public static ExpenseEntity mapToExpenseEntity(ExpenseDto expenseDto,ProfileEntity profile,CategoryEntity category) {
        return ExpenseEntity.builder()
                .name(expenseDto.getName())
                .icon(expenseDto.getIcon())
                .date(expenseDto.getDate())
                .amount(expenseDto.getAmount())
                .profile(profile)
                .category(category)
                .build();

    }
    public static ExpenseDto mapToExpenseDto(ExpenseEntity expenseEntity) {
        return ExpenseDto.builder()
                .id(expenseEntity.getId())
                .name(expenseEntity.getName())
                .icon(expenseEntity.getIcon())
                .date(expenseEntity.getDate())
                .amount(expenseEntity.getAmount())
                .categoryId(expenseEntity.getCategory() != null ? expenseEntity.getCategory().getId() : null)  
                .categoryName(expenseEntity.getCategory() != null ? expenseEntity.getCategory().getName() : null)
                .createdAt(expenseEntity.getCreatedAt())
                .updatedAt(expenseEntity.getUpdatedAt())
                .build();
    }

    public static IncomeEntity mapToIncomeEntity(IncomeDto incomeDto, ProfileEntity profile, CategoryEntity category) {
        return IncomeEntity.builder()
                .name(incomeDto.getName())
                .icon(incomeDto.getIcon())
                .date(incomeDto.getDate())
                .amount(incomeDto.getAmount())
                .profile(profile)
                .category(category)
                .build();

    }
    public static  IncomeDto mapToIncomeDto(IncomeEntity incomeEntity) {
        return IncomeDto.builder()
                .id(incomeEntity.getId())
                .name(incomeEntity.getName())
                .icon(incomeEntity.getIcon())
                .date(incomeEntity.getDate())
                .amount(incomeEntity.getAmount())
                .categoryId(incomeEntity.getCategory() != null ? incomeEntity.getCategory().getId() : null)
                .categoryName(incomeEntity.getCategory() != null ? incomeEntity.getCategory().getName() : null)
                .createdAt(incomeEntity.getCreatedAt())
                .updatedAt(incomeEntity.getUpdatedAt())
                .build();
    }
}
