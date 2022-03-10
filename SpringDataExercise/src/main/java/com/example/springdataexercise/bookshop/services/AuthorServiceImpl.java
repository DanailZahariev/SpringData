package com.example.springdataexercise.bookshop.services;

import com.example.springdataexercise.bookshop.entity.Author;
import com.example.springdataexercise.bookshop.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Author getRandomAuthor() {

        long size = authorRepository.count();

        int authorId = new Random().nextInt((int) size) + 1;

        return this.authorRepository.findById(authorId).get();
    }
}
