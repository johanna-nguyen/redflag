package com.project.redflag.category.mapper;

import com.project.redflag.category.dto.request.CategoryRequest;
import com.project.redflag.category.dto.response.CategoryResponse;
import com.project.redflag.category.entity.Category;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    // convert request to entity
    Category toEntity(CategoryRequest categoryRequest);

    // convert entity to response
    CategoryResponse toResponse (Category category);

    // convert list entity to response list
    List<CategoryResponse> toResponseList (List<Category> categoryList);
}
