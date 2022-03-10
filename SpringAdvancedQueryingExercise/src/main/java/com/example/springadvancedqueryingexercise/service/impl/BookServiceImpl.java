package com.example.springadvancedqueryingexercise.service.impl;

import com.example.springadvancedqueryingexercise.model.entity.*;
import com.example.springadvancedqueryingexercise.repository.BookRepository;
import com.example.springadvancedqueryingexercise.service.AuthorService;
import com.example.springadvancedqueryingexercise.service.BookService;
import com.example.springadvancedqueryingexercise.service.CategoryService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private static final String BOOKS_FILE_PATH = "SpringAdvancedQueryingExercise/src/main/resources/files/books.txt";

    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final CategoryService categoryService;

    public BookServiceImpl(BookRepository bookRepository, AuthorService authorService, CategoryService categoryService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.categoryService = categoryService;
    }

    @Override
    public void seedBooks() throws IOException {
        if (bookRepository.count() > 0) {
            return;
        }

        Files
                .readAllLines(Path.of(BOOKS_FILE_PATH))
                .forEach(row -> {
                    String[] bookInfo = row.split("\\s+");

                    Book book = createBookFromInfo(bookInfo);

                    bookRepository.save(book);
                });
    }

    @Override
    public List<Book> findAllBooksAfterYear(int year) {
        return bookRepository
                .findAllByReleaseDateAfter(LocalDate.of(year, 12, 31));
    }

    @Override
    public List<String> findAllAuthorsWithBooksWithReleaseDateBeforeYear(int year) {
        return bookRepository
                .findAllByReleaseDateBefore(LocalDate.of(year, 1, 1))
                .stream()
                .map(book -> String.format("%s %s", book.getAuthor().getFirstName(),
                        book.getAuthor().getLastName()))
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(String firstName, String lastName) {
        return bookRepository
                .findAllByAuthor_FirstNameAndAuthor_LastNameOrderByReleaseDateDescTitle(firstName, lastName)
                .stream()
                .map(book -> String.format("%s %s %d",
                        book.getTitle(),
                        book.getReleaseDate(),
                        book.getCopies()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> getBooksByAgeRestriction(AgeRestriction age) {
        return this.bookRepository.findAllBooksByAgeRestriction(age);
    }

    @Override
    public List<String> findAllGoldBookTittlesWithLessThan5000Copies() {
        return this.bookRepository.findAllByEditionTypeAndCopiesLessThan(EditionType.GOLD, 5000)
                .stream().map(Book::getTitle).collect(Collectors.toList());
    }

    @Override
    public List<String> findAllBookTittlesWithPriceLessThan5OrMoreThan40() {
        return this.bookRepository.findAllByPriceLessThanOrPriceGreaterThan(BigDecimal.valueOf(5), BigDecimal.valueOf(40))
                .stream().map(book -> String.format("%s - $%.2f", book.getTitle(), book.getPrice()))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findNotReleasedBooksInYear(int year) {

        LocalDate lower = LocalDate.of(year, 1, 1);
        LocalDate upper = LocalDate.of(year, 12, 31);

        return bookRepository.findAllByReleaseDateBeforeOrReleaseDateAfter(lower, upper)
                .stream().map(Book::getTitle).collect(Collectors.toList());

    }

    @Override
    public List<String> findAllBookBeforeDate(LocalDate localDate) {
        return bookRepository.findAllByReleaseDateBefore(localDate)
                .stream().map(b -> String.format("%s %s %.2f", b.getTitle(), b.getEditionType().name(), b.getPrice()))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findBooksByContainString(String line) {
        return bookRepository.findByTitleContaining(line).stream().map(Book::getTitle).collect(Collectors.toList());
    }


    @Override
    public List<String> findAuthorLastnameStartsWith(String line) {
        return bookRepository.findAllByAuthor_LastNameStartsWith(line)
                .stream().map(b -> String.format("%s (%s %s)", b.getTitle(), b.getAuthor().getFirstName(), b.getAuthor().getLastName()))
                .collect(Collectors.toList());
    }

    @Override
    public int findCountOfBooksWithTittleLongerThan(int tittleLength) {
        return bookRepository.countBookByTitleIsGreaterThan(tittleLength);
    }

    @Override
    public int addCopiesToBook(String date, int copies) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd MMM yyyy");
        LocalDate after = LocalDate.parse(date, format);

        return this.bookRepository.addCopiesToBookAfter(after, copies);
    }

    @Override
    public List<String> getBookInfo(String book) {
        return this.bookRepository.findBooksByTitle(book).stream().map(b ->
                String.format("%s %s %s %.2f", b.getTitle(), b.getEditionType()
                        , b.getAgeRestriction(), b.getPrice())).collect(Collectors.toList());
    }

    @Override
    public int deleteWithLessCopiesThan(int amount) {
        return this.bookRepository.deleteByCopiesLessThan(amount);
    }

    @Override
    public int findAuthorTotalBooks(String firstName, String lastName) {
        return this.bookRepository.getTotalBooksByAuthor(firstName, lastName);
    }


    private Book createBookFromInfo(String[] bookInfo) {
        EditionType editionType = EditionType.values()[Integer.parseInt(bookInfo[0])];
        LocalDate releaseDate = LocalDate
                .parse(bookInfo[1], DateTimeFormatter.ofPattern("d/M/yyyy"));
        Integer copies = Integer.parseInt(bookInfo[2]);
        BigDecimal price = new BigDecimal(bookInfo[3]);
        AgeRestriction ageRestriction = AgeRestriction
                .values()[Integer.parseInt(bookInfo[4])];
        String title = Arrays.stream(bookInfo)
                .skip(5)
                .collect(Collectors.joining(" "));

        Author author = authorService.getRandomAuthor();
        Set<Category> categories = categoryService
                .getRandomCategories();

        return new Book(editionType, releaseDate, copies, price, ageRestriction, title, author, categories);
    }
}
