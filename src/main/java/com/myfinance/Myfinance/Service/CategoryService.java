package com.myfinance.Myfinance.Service;

import com.myfinance.Myfinance.Entity.CategoryEntity;
import com.myfinance.Myfinance.Entity.ProfileEntity;
import com.myfinance.Myfinance.Mapper.Mapper;
import com.myfinance.Myfinance.Repository.CategoryRepository;
import com.myfinance.Myfinance.dto.CategoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final ProfileService profileService;
    private final CategoryRepository categoryRepository;

    public CategoryDto saveCategory(CategoryDto categoryDto) {
        ProfileEntity profile = profileService.getCurrentProfile();
        if(categoryRepository.existsByNameAndProfileId(categoryDto.getName(),profile.getId())) {
       throw  new ResponseStatusException(HttpStatus.BAD_REQUEST,"Category with this name already exists");
        }
        CategoryEntity categoryEntity = Mapper.mapToCategoryEntity(categoryDto,profile);
        CategoryEntity savedCategory = categoryRepository.save(categoryEntity);
    }
}
