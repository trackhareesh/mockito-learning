package com.myspringprojects.rnd.mockitolearning.annotations.support;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Use the ExtendWith JUnit5 annotation to make mockito
// process the Mockito annotations. Only then, the @Mock annotations
// will be interpreted by Mockito.
@ExtendWith(MockitoExtension.class)
@DisplayName("Mockito Annotations")
class BookServiceTest {

    // Use the InjectMocks annotation to let Mockito create an instance of the
    // class under test (e.g. BookService) and inject into it all required mock
    // dependencies specified in the constructor.
    @InjectMocks
    private BookService bookService;

    // Create a mock object using annotation. (Also use the ExtendWith annotation on the class)
    @Mock
    private BookRepository bookRepository;

    @Test
    @DisplayName("Stub testing with Mockito Annotations")
    public void testGetNewBooksWithAppliedDiscountWithAnnotations() {

        // Create the collection that we need to return from the stub.
        List<Book> books = List.of(
                new Book("1234", "Mockito in Action", 500, LocalDate.now()),
                new Book("1235", "JUnit5 in Action", 400, LocalDate.now())
        );

        // Configure our mock bookRepository object to return the above collection, when
        // the findNewBooks method is invoked (with a parameter of 7)
        Mockito.when(bookRepository.findNewBooks(7)).thenReturn(books);

        List<Book> newBooks = bookService.getNewBooksWithAppliedDiscount(10, 7);

        // First check the size of the collection. It should be the same as the collection returned by the
        // stubbed repository.
        assertEquals(2, newBooks.size());

        // Verify that 10% discount is applied to both the books in the returned list.
        assertEquals(450, newBooks.get(0).getPrice());
        assertEquals(360, newBooks.get(1).getPrice());
    }
}