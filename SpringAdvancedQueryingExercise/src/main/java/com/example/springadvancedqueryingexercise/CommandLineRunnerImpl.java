package com.example.springadvancedqueryingexercise;

import com.example.springadvancedqueryingexercise.model.entity.AgeRestriction;
import com.example.springadvancedqueryingexercise.model.entity.Book;
import com.example.springadvancedqueryingexercise.service.AuthorService;
import com.example.springadvancedqueryingexercise.service.BookService;
import com.example.springadvancedqueryingexercise.service.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final BookService bookService;
    private final BufferedReader bufferedReader;


    public CommandLineRunnerImpl(CategoryService categoryService,
                                 AuthorService authorService,
                                 BookService bookService, BufferedReader bufferedReader) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService = bookService;
        this.bufferedReader = bufferedReader;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Enter exercise number: ");
        int exerciseNumber = Integer.parseInt(bufferedReader.readLine());
        switch (exerciseNumber) {
            case 1:
                bookTittlesByAgeRestriction();
                break;
            case 2:
                getGoldenBook();
                break;
            case 3:
                getBookByPrice();
                break;
            case 4:
                notReleasedBooks();
                break;
            case 5:
                getBooksBeforeReleaseDate();
                break;
        }


    }

    private void getBooksBeforeReleaseDate() throws IOException {
        System.out.println("Enter release date: ");

        LocalDate localDate = LocalDate.parse(bufferedReader.readLine(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));

        bookService.findAllBookBeforeDate(localDate).forEach(System.out::println);
    }

    private void notReleasedBooks() throws IOException {
        System.out.println("Enter year: ");

        int year = Integer.parseInt(bufferedReader.readLine());

        bookService.findNotReleasedBooksInYear(year).forEach(System.out::println);
    }

    private void getBookByPrice() {
        this.bookService.findAllBookTittlesWithPriceLessThan5OrMoreThan40()
                .forEach(System.out::println);
    }

    private void getGoldenBook() {
        bookService.findAllGoldBookTittlesWithLessThan5000Copies().forEach(System.out::println);
    }

    private void bookTittlesByAgeRestriction() throws IOException {
        System.out.println("Enter age restriction: ");

        AgeRestriction age = AgeRestriction.valueOf(bufferedReader.readLine().toUpperCase());

        List<Book> books = this.bookService.getBooksByAgeRestriction(age);
        books.forEach(b -> System.out.printf("%s%n", b.getTitle()));
    }


    private void seedData() throws IOException {
        categoryService.seedCategories();
        authorService.seedAuthors();
        bookService.seedBooks();
    }
}
