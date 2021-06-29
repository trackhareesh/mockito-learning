package com.myspringprojects.rnd.mockitolearning.testdoubles.mock;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class BookServiceTest {

    @Test
    @DisplayName("Use Mock to test the addBook Behaviour")
    void testAddBookWithMock() {
        BookRepositoryMock bookRepository = new BookRepositoryMock();
        BookService bookService = new BookService(bookRepository);

        Book book1 = new Book("1234", "Mockito in Action", 500, LocalDate.now());
        Book book2 = new Book("1235", "JUnit5 in Action", 400, LocalDate.now());

        bookService.addBook(book1);
        bookService.addBook(book2);

        // The verification is done by the mock.
        bookRepository.verify(book2, 2);

    }
}