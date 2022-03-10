package com.example.springadvancedqueryingexercise.service;

import com.example.springadvancedqueryingexercise.model.entity.AgeRestriction;
import com.example.springadvancedqueryingexercise.model.entity.Book;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public interface BookService {
    void seedBooks() throws IOException;

    List<Book> findAllBooksAfterYear(int year);

    List<String> findAllAuthorsWithBooksWithReleaseDateBeforeYear(int year);

    List<String> findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(String firstName, String lastName);


    List<Book> getBooksByAgeRestriction(AgeRestriction age);

    List<String> findAllGoldBookTittlesWithLessThan5000Copies();

    List<String> findAllBookTittlesWithPriceLessThan5OrMoreThan40();

    List<String> findNotReleasedBooksInYear(int year);

    List<String> findAllBookBeforeDate(LocalDate localDate);

    List<String> findBooksByContainString(String line);

    List<String> findAuthorLastnameStartsWith(String line);

    int findCountOfBooksWithTittleLongerThan(int tittleLength);

    int addCopiesToBook(String date, int copies);

    List<String> getBookInfo(String book);

    int deleteWithLessCopiesThan(int amount);

    int findAuthorTotalBooks(String firstName, String lastName);
}
