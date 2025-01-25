package com.tbh.backend.controller;


import com.tbh.backend.dto.CategoryDto;
import com.tbh.backend.dto.ChallengeDTO;
import com.tbh.backend.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        List<CategoryDto> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{id}/challenges")
    public ResponseEntity<List<ChallengeDTO>> getChallengesByCategory(@PathVariable Long id) {
        List<ChallengeDTO> challenges = categoryService.getChallengesByCategoryId(id);
        return ResponseEntity.ok(challenges);
    }

    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto) {
        CategoryDto createdCategory = categoryService.createCategory(categoryDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCategory);
    }
}
