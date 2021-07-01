package com.myspringprojects.rnd.mockitolearning.behaviour.verification;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Behaviour Verification")
class BookServiceTest {

    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    @Test
    @DisplayName("Verify Number of Interactions - Price > 500")
    void testAddBookWithPriceGreaterThan500() {

        Book book = new Book(null, "JUnit 5 in Action", 600, LocalDate.now());
        bookService.addBook(book);

        // Verify that the save method is invoked exactly once, since the price is greater than 500.
        Mockito.verify(bookRepository, Mockito.times(1)).save(book);
    }

    @Test
    @DisplayName("Verify Number of Interactions - Price = 500")
    void testAddBookWithPriceEqualTo500() {

        BookRequest bookRequest = new BookRequest("JUnit 5 in Action", 500, LocalDate.now());
        Book book = new Book(null, "JUnit 5 in Action", 500, LocalDate.now());
        bookService.addBook(bookRequest);

        // Verify that the save methohd is called 0 times (i.e. never called), as the
        // book price is <= 500
        Mockito.verify(bookRepository, Mockito.times(0)).save(book);
    }

    @Test
    @DisplayName("Verify Number of Interactions - Price <= 500")
    void testAddBookWithPriceLessThan500() {

        BookRequest bookRequest = new BookRequest("JUnit 5 in Action", 400, LocalDate.now());
        Book book = new Book(null, "JUnit 5 in Action", 500, LocalDate.now());
        bookService.addBook(bookRequest);

        // Verify that the save methohd is called 0 times (i.e. never called), as the
        // book price is <= 500
        Mockito.verify(bookRepository, Mockito.times(0)).save(book);
    }
}