package com.myfinance.Myfinance.Controller;

import com.myfinance.Myfinance.Service.CategoryService;
import com.myfinance.Myfinance.dto.CategoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

     @PostMapping("/saveCategory")
    public ResponseEntity<CategoryDto> saveCategory(@RequestBody CategoryDto categoryDto) {
        CategoryDto savedCategory = categoryService.saveCategory(categoryDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCategory);
    }

    @GetMapping("/fetchAll")
    public ResponseEntity<List<CategoryDto>> getCategoriesForCurrentUser() {
         List<CategoryDto> categories = categoryService.getCategoriesForCurrentUser();
         return ResponseEntity.ok(categories);
    }

    @GetMapping("/{type}")
    public ResponseEntity<List<CategoryDto>> getCategoriesTypeandForCurrentUser(@PathVariable String type) {
         List<CategoryDto> categories = categoryService.getCategoriesTypeandForCurrentUser(type);
         return ResponseEntity.ok(categories);

    }
     @PutMapping("/updateCategory/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable Long categoryId,@RequestBody CategoryDto categoryDto) {
         CategoryDto updatedCategory = categoryService.updateCategory(categoryId,categoryDto);
         return ResponseEntity.ok(updatedCategory);
    }

}
