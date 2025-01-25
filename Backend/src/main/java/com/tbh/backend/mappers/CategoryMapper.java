package com.tbh.backend.mappers;

import com.tbh.backend.dto.CategoryDto;
import com.tbh.backend.entity.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public CategoryDto mapToDTO(Category category) {
        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }

    public Category mapToEntity(CategoryDto categoryDto) {
        return Category.builder()
                .id(categoryDto.getId())
                .name(categoryDto.getName())
                .build();
    }
}
