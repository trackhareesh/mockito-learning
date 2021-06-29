package com.myspringprojects.rnd.mockitolearning.testdoubles.fake;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class BookServiceTest {

    @Test
    void testWithFakeService() {
        // Create an instance of the bookService injecting a FakeBookRepository.
        BookService bookService = new BookService(new FakeBookRepository());

        // Add 2 books by calling the service add method.
        bookService.addBook(new Book("1234", "Mockito in Action", 250, LocalDate.now()));
        bookService.addBook(new Book("1235", "JUnit5 in Action", 200, LocalDate.now()));

        // Verify if the findAll method on the service returns the number of books added.
        assertEquals(2, bookService.findNoOfBooks());
    }

}