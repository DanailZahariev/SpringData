package com.example.springadvancedqueryingexercise.repository;


import com.example.springadvancedqueryingexercise.model.entity.AgeRestriction;
import com.example.springadvancedqueryingexercise.model.entity.Book;
import com.example.springadvancedqueryingexercise.model.entity.EditionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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

    List<Book> findAllByReleaseDateBeforeOrReleaseDateAfter(LocalDate lower, LocalDate higher);

    List<Book> findByTitleContaining(String line);

    List<Book> findAllByAuthor_LastNameStartsWith(String line);

    @Query("SELECT COUNT(b) FROM Book b WHERE length(b.title) > :length")
    int countBookByTitleIsGreaterThan(@Param(value = "length") int length);

    @Modifying
    @Transactional
    @Query("UPDATE Book b SET b.copies = b.copies + :amount WHERE b.releaseDate > :date")
    int addCopiesToBookAfter(LocalDate date, int amount);

    List<Book> findBooksByTitle(String book);

    @Transactional
    int deleteByCopiesLessThan(int amount);

    @Procedure("total_books_by_author")
    int getTotalBooksByAuthor(@Param("first_name") String firstName,
                              @Param("last_name") String lastName);
}
