package com.example.springadvancedqueryingexercise.service;

import com.example.springadvancedqueryingexercise.model.entity.Category;

import java.io.IOException;
import java.util.Set;

public interface CategoryService {
    void seedCategories() throws IOException;

    Set<Category> getRandomCategories();
}
