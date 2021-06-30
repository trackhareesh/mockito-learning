package com.myspringprojects.rnd.mockitolearning.testdoubles.stub;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookServiceTest {

    @Test
    @DisplayName("Stubbed BookService to test BusinessLogic")
    void testGetNewBooksWithAppliedDiscountWithStub() {
        BookRepository bookRepository = new BookRepositoryStub();
        BookService bookService = new BookService(bookRepository);

        List<Book> newBooks = bookService.getNewBooksWithAppliedDiscount(10, 7);

        // First check the size of the collection. It should be the same as the collection returned by the
        // stubbed repository.
        assertEquals(2, newBooks.size());

        // Verify that 10% discount is applied to both the books in the returned list.
        assertEquals(450, newBooks.get(0).getPrice());
        assertEquals(360, newBooks.get(1).getPrice());

    }

    @Test
    @DisplayName("Stubbed BookService with Mockito")
    void testGetNewBooksWithAppliedDiscountWithMockito() {

        // Create a BookRepository mock implementation with Mockito.
        BookRepository bookRepository = Mockito.mock(BookRepository.class);

        // Create the collection that we need to return from the stub.
        List<Book> books = List.of(
                new Book("1234", "Mockito in Action", 500, LocalDate.now()),
                new Book("1235", "JUnit5 in Action", 400, LocalDate.now())
        );

        // Configure our mock bookRepository object to return the above collection, when
        // the findNewBooks method is invoked (with a parameter of 7)
        Mockito.when(bookRepository.findNewBooks(7)).thenReturn(books);

        // Create a BookService object, with the mock BookRepository injected.
        BookService bookService = new BookService(bookRepository);

        List<Book> newBooks = bookService.getNewBooksWithAppliedDiscount(10, 7);

        // First check the size of the collection. It should be the same as the collection returned by the
        // stubbed repository.
        assertEquals(2, newBooks.size());

        // Verify that 10% discount is applied to both the books in the returned list.
        assertEquals(450, newBooks.get(0).getPrice());
        assertEquals(360, newBooks.get(1).getPrice());
    }
}