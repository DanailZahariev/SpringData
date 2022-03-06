package com.example.advquerying;

import com.example.advquerying.entities.Size;
import com.example.advquerying.repository.ShampooRepository;
import com.example.advquerying.service.IngredientService;
import com.example.advquerying.service.ShampooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Component
public class Runner implements CommandLineRunner {

    private final ShampooService shampooService;
    private final IngredientService ingredientService;


    @Autowired
    public Runner(ShampooService shampooService,
                  IngredientService ingredientService) {
        this.shampooService = shampooService;
        this.ingredientService = ingredientService;
    }

    @Override
    public void run(String... args) throws Exception {

        this.shampooService.selectBySize(Size.MEDIUM).
                forEach(s -> System.out.printf("%s %s %.2f%n", s.getBrand(), s.getSize(), s.getPrice()));

        this.shampooService.selectBySizeOrLabelId(Size.MEDIUM, 10).
                forEach(s -> System.out.printf("%s %s %.2f%n", s.getBrand(), s.getSize(), s.getPrice()));

        this.shampooService.selectMoreExpensiveThan(BigDecimal.valueOf(5)).
                forEach(s -> System.out.printf("%s %s %.2f%n", s.getBrand(), s.getSize(), s.getPrice()));

        this.ingredientService.selectNameStartWith("M").
                forEach(i -> System.out.println(i.getName()));

        this.ingredientService.selectInName(List.of("Lavender", "Herbs", "Apple"))
                .forEach(i -> System.out.println(i.getName()));

        int countPriceLowerThan = shampooService.countPriceLowerThan(BigDecimal.valueOf(8.5));
        System.out.println(countPriceLowerThan);

        Set<String> names = Set.of("Berry", "Mineral-Collagen");
        this.shampooService.selectByIngredients(names).forEach(s -> System.out.printf("%s%n", s.getBrand()));


        this.shampooService.selectByIngredientCount(2).forEach(s -> System.out.printf("%s%n", s.getBrand()));


    }
}
