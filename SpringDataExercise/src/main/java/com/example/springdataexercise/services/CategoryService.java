package com.example.springdataexercise.services;

import com.example.springdataexercise.entity.Category;

import java.util.Set;

public interface CategoryService {

    Set<Category> getRandomCategories();
}
