package com.example.advquerying.service;

import com.example.advquerying.entities.Ingredient;

import java.util.List;

public interface IngredientService {

    List<Ingredient> selectNameStartWith(String start);

    List<Ingredient> selectInName(List<String> names);
}
