package com.myfinance.Myfinance.Controller;

import com.myfinance.Myfinance.Service.CategoryService;
import com.myfinance.Myfinance.dto.CategoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    public ResponseEntity<CategoryDto> saveCategory(CategoryDto categoryDto) {
}
