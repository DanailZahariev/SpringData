package com.example.service;

import com.example.model.dtos.CategorySeedDto;
import com.example.model.entity.Category;

import java.util.List;
import java.util.Set;

public interface CategoryService {

    void seedCategories(List<CategorySeedDto> categories);

    long getEntityCount();

    Set<Category> getRandomCategories();
}
