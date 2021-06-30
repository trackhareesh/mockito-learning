package com.myspringprojects.rnd.mockitolearning.testdoubles.spy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("SpyTestDouble")
class BookServiceTest {

    @Test
    @DisplayName("Use spy to test the addBook Behaviour")
    void testAddBookWithSpy() {
        BookRepositorySpy bookRepository = new BookRepositorySpy();
        BookService bookService = new BookService(bookRepository);

        Book book1 = new Book("1234", "Mockito in Action", 500, LocalDate.now());
        Book book2 = new Book("1235", "JUnit5 in Action", 400, LocalDate.now());

        bookService.addBook(book1);
        bookService.addBook(book2);

        // Check if save was called 2 times on the bookRepository.
        assertEquals(2, bookRepository.timesCalled());

        // Check if the bookRepository was last called with book2.
        assertTrue(bookRepository.calledWith(book2), "The Book Repository was not last called with book2");

    }

    @Test
    @DisplayName("Use Spy with Mockito to test addBook Behaviour")
    void testAddBookWithSpyWithMockito() {

        BookRepository bookRepository = Mockito.spy(BookRepository.class);
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