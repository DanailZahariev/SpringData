package com.example.springadvancedqueryingexercise.repository;


import com.example.springadvancedqueryingexercise.model.entity.AgeRestriction;
import com.example.springadvancedqueryingexercise.model.entity.Book;
import com.example.springadvancedqueryingexercise.model.entity.EditionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByReleaseDateAfter(LocalDate releaseDateAfter);

    List<Book> findAllByReleaseDateBefore(LocalDate releaseDateBefore);

    List<Book> findAllByAuthor_FirstNameAndAuthor_LastNameOrderByReleaseDateDescTitle(String author_firstName, String author_lastName);

    List<Book> findAllBooksByAgeRestriction(AgeRestriction age);

    List<Book> findAllByEditionTypeAndCopiesLessThan(EditionType editionType, Integer copies);

    List<Book> findAllByPriceLessThanOrPriceGreaterThan(BigDecimal one, BigDecimal two);

    List<Book> findAllByReleaseDateBeforeOrReleaseDateAfter(LocalDate lower,LocalDate higher);

    List<Book> findByTitleContaining(String line);

}
