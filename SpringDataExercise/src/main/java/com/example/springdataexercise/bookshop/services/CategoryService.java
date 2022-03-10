package com.example.springdataexercise.bookshop.services;

import com.example.springdataexercise.bookshop.entity.Category;

import java.util.Set;

public interface CategoryService {

    Set<Category> getRandomCategories();
}
