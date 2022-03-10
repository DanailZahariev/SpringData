package com.example.springadvancedqueryingexercise.repository;


import com.example.springadvancedqueryingexercise.model.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query("SELECT a FROM Author a ORDER BY a.books.size DESC")
    List<Author> findAllByBooksSizeDESC();

    List<Author> findAuthorByFirstNameEndingWith(String line);

    @Query("SELECT a, SUM(b.copies) AS countBook FROM Author a" +
            " JOIN a.books AS b GROUP BY b.author ORDER BY countBook DESC ")
    List<Author> getAuthorTotalCopies();

}
