package com.project.redflag.category.service;

import com.project.redflag.category.dto.request.CategoryRequest;
import com.project.redflag.category.dto.response.CategoryResponse;
import com.project.redflag.category.entity.Category;
import com.project.redflag.category.mapper.CategoryMapper;
import com.project.redflag.category.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper){
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    public CategoryResponse createCategory(CategoryRequest categoryRequest){
        Category category = categoryMapper.toEntity(categoryRequest);
        Category saved = categoryRepository.save(category);
        return categoryMapper.toResponse(saved);
    }

    public List<CategoryResponse> getAllCategories(){
        List<Category> categoryList = categoryRepository.findAll();
        return categoryMapper.toResponseList(categoryList);
    }

    public CategoryResponse updateCategory (Long id, CategoryRequest updatedRequest){
        Category category = categoryRepository.getReferenceById(id);
        category.setName(updatedRequest.getName());
        Category saved = categoryRepository.save(category);
        return categoryMapper.toResponse(saved);
    }

    public void deleteCategory(Long id){
        categoryRepository.deleteById(id);
    }
}
