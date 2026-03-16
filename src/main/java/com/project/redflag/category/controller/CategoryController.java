package com.project.redflag.category.controller;

import com.project.redflag.category.dto.request.CategoryRequest;
import com.project.redflag.category.dto.response.CategoryResponse;
import com.project.redflag.category.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR')")
    @PostMapping
    @Operation(summary = "create a category")
    @Tag(name = "Category API")
    public CategoryResponse createCategory(@Valid @RequestBody CategoryRequest categoryRequest){
        return categoryService.createCategory(categoryRequest);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR', 'REPORTER')")
    @GetMapping
    @Operation(summary = "get all categories")
    @Tag(name = "Category API")
    public List<CategoryResponse> getAllCategories(){
        return categoryService.getAllCategories();
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR')")
    @PutMapping("/{id}")
    @Operation(summary = "update a category")
    @Tag(name = "Category API")
    public CategoryResponse updateCategory(@PathVariable Long id, @RequestBody CategoryRequest  categoryRequest){
        return categoryService.updateCategory(id, categoryRequest);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    @Operation(summary = "delete a category")
    @Tag(name = "Category API")
    public void deleteCategory(@PathVariable Long id){
        categoryService.deleteCategory(id);
    }
}
