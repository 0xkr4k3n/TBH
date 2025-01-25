package com.tbh.backend.service;

import com.tbh.backend.dto.CategoryDto;
import com.tbh.backend.dto.ChallengeDTO;
import com.tbh.backend.entity.Category;
import com.tbh.backend.mappers.CategoryMapper;
import com.tbh.backend.mappers.ChallengeMapper;
import com.tbh.backend.repository.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final ChallengeMapper challengeMapper;

    public List<CategoryDto> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(categoryMapper::mapToDTO)
                .collect(Collectors.toList());
    }

    public List<ChallengeDTO> getChallengesByCategoryId(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new EntityNotFoundException("Category not found"));

        return category.getChallenges().stream()
                .map(challengeMapper::mapToDTO)
                .collect(Collectors.toList());
    }


    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = categoryMapper.mapToEntity(categoryDto);
        Category savedCategory = categoryRepository.save(category);
        return categoryMapper.mapToDTO(savedCategory);
    }
}
