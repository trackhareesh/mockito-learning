package com.myspringprojects.rnd.mockitolearning.testdoubles.mock;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

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

    @Test
    @DisplayName("Use Mock with Mockito to test addBook Behaviour")
    void testAddBookWithMockWithMockito() {

        BookRepository bookRepository = Mockito.mock(BookRepository.class);
        BookService bookService = new BookService(bookRepository);

        Book book1 = new Book("1234", "Mockito in Action", 500, LocalDate.now());
        Book book2 = new Book("1235", "JUnit5 in Action", 400, LocalDate.now());

        bookService.addBook(book1);
        bookService.addBook(book2);

        // Verify that the save method was called on the BookRepository with book1, exactly one time and
        // with book2 exactly one time.
        Mockito.verify(bookRepository, Mockito.times(1)).save(book1);
        Mockito.verify(bookRepository, Mockito.times(1)).save(book2);


    }
}