package com.example.advquerying.service;

import com.example.advquerying.entities.Ingredient;
import com.example.advquerying.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientServiceImpl implements IngredientService {

    @Autowired
    private IngredientRepository ingredientRepository;


    @Override
    public List<Ingredient> selectNameStartWith(String start) {
        return this.ingredientRepository.findByNameStartingWith(start);
    }

    @Override
    public List<Ingredient> selectInName(List<String> names) {
        return this.ingredientRepository.findByNameInOrderByPriceAsc(names);
    }
}
