package com.example.springdataexercise.bookshop.repository;

import com.example.springdataexercise.bookshop.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
