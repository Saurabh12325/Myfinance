package com.myfinance.Myfinance.Service;

import com.myfinance.Myfinance.Entity.ProfileEntity;
import com.myfinance.Myfinance.Repository.CategoryRepository;
import com.myfinance.Myfinance.dto.CategoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final ProfileService profileService;
    private final CategoryRepository categoryRepository;

    public CategoryDto saveCategory(CategoryDto categoryDto) {
        ProfileEntity profile = profileService.getCurrentProfile();
        if(categoryRepository.existsByNameAndProfileId(categoryDto.getName(),profile.getId())) {

    }
}
