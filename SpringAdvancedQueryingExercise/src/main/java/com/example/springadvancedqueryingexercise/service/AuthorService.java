package com.example.springadvancedqueryingexercise.service;

import com.example.springadvancedqueryingexercise.model.entity.Author;

import java.io.IOException;
import java.util.List;

public interface AuthorService {
    void seedAuthors() throws IOException;

    Author getRandomAuthor();

    List<String> getAllAuthorsOrderByCountOfTheirBooks();

    List<String> findAllAuthorsWithFirstNameEndWith(String line);

}
