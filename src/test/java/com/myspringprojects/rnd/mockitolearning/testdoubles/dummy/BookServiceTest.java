package com.myspringprojects.rnd.mockitolearning.testdoubles.dummy;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class BookServiceTest {

    @Test
    @DisplayName("Dummy Test Double without Mockito")
    void testWithDummyTestDouble() {
        // Create an instance of the bookService injecting a FakeBookRepository and the DummyEmailService.
        // Here we use both the Fake Test Double and the Dummy Test Double patterns.
        // FakeBookRepository represents the Fake implementation that will be used in this unit test.
        // DummyEmailService is irrelevant to this unit test, but injected to satisfy compile time dependency.
        BookService bookService = new BookService(new FakeBookRepository(), new DummyEmailService());

        // Add 2 books by calling the service add method.
        bookService.addBook(new Book("1234", "Mockito in Action", 250, LocalDate.now()));
        bookService.addBook(new Book("1235", "JUnit5 in Action", 200, LocalDate.now()));

        // Verify if the findAll method on the service returns the number of books added.
        assertEquals(2, bookService.findNoOfBooks());
    }
}