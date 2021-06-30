package com.myspringprojects.rnd.mockitolearning.testdoubles.dummy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("DummyTestDouble")
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

    @Test
    @DisplayName("Dummy Test Double with Mockito")
    void testDummyTestDoubleWithMockito() {

        // First create a mock object for the BookRepository and the EmailService.
        // Note that, we do not have to implement these classes manually.
        // Mockito wil dynamically provide a mock implementation.
        BookRepository bookRepository = Mockito.mock(BookRepository.class);
        EmailService emailService = Mockito.mock(EmailService.class);

        BookService bookService = new BookService(bookRepository, emailService);

        Book book1 = new Book("1234", "Mockito in Action", 250, LocalDate.now());
        Book book2 = new Book("1235", "JUnit5 in Action", 200, LocalDate.now());

        Collection<Book> books = List.of(book1, book2);

        // Stub the findAll() method of the bookRepository.
        Mockito.when(bookRepository.findAll()).thenReturn(books);

        // Verify that when the BookService.findNoOfBooks() is called, it internally uses the
        // value returned by the BookRepository. Since, we stubbed the findAll() method of the repository
        // to return 2 hard coded books, the same books will be used here in this execution.
        assertEquals(2, bookService.findNoOfBooks());
    }
}