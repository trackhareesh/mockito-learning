package com.myspringprojects.rnd.mockitolearning.testdoubles.stub;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
}