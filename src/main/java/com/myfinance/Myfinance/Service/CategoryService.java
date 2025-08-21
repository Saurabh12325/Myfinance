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

import java.util.List;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final ProfileService profileService;
    private final CategoryRepository categoryRepository;

    public CategoryDto saveCategory(CategoryDto categoryDto) {
        ProfileEntity profile = profileService.getCurrentProfile();
        if(categoryRepository.existsByNameAndProfileId(categoryDto.getName(),profile.getId())) {
       throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Category with this name already exists");
        }
        CategoryEntity categoryEntity = Mapper.mapToCategoryEntity(categoryDto,profile);
        categoryEntity = categoryRepository.save(categoryEntity);
        return Mapper.mapToCategoryDto(categoryEntity);
    }

    //get categories for the current user

    public List<CategoryDto> getCategoriesForCurrentUser() {
        ProfileEntity profile = profileService.getCurrentProfile();
        List<CategoryEntity> categories = categoryRepository.findByProfileId(profile.getId());
        return categories
                .stream()
                .map(Mapper::mapToCategoryDto)
                .toList();
    }

    public List<CategoryDto> getCategoriesTypeandForCurrentUser(String type) {
        ProfileEntity profile = profileService.getCurrentProfile();
        List<CategoryEntity> categories = categoryRepository.findByTypeAndProfileId(type, profile.getId());
        return categories
                .stream()
                .map(Mapper::mapToCategoryDto)
                .toList();
    }

    public CategoryDto updateCategory(Long CategoryId,CategoryDto categoryDto) {
        ProfileEntity profile = profileService.getCurrentProfile();
        CategoryEntity categoryEntity = categoryRepository.findByIdAndProfileId(CategoryId,profile.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Category not found"));
        categoryEntity.setName(categoryDto.getName());
        categoryEntity.setIcon(categoryDto.getIcon());
        categoryEntity = categoryRepository.save(categoryEntity);
        return Mapper.mapToCategoryDto(categoryEntity);
    }





}
