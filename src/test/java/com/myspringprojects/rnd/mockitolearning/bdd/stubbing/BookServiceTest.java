package com.myspringprojects.rnd.mockitolearning.bdd.stubbing;

import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.BDDAssertions.then;

@ExtendWith(MockitoExtension.class)
@DisplayName("BDD Tests with Mockito")
class BookServiceTest {

    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    @Test
    @DisplayName("Traditional Non BDD Testing - New Books With Applied Discount")
    void testGetNewBooksWithAppliedDiscountInNonBDDStyle() {

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


    @Test
    @DisplayName("Given NewBooks When GetNewBooksWithAppliedDiscount " +
            "Is Called Then NewBooksWithAppliedDiscount Is Returned")
    public void testNewBooksWithAppliedDiscountBDDStyle() {
        //////////////// Arrange Step (Given)

        // Mock objects are usually created in the Arrange step, but since we use annotations, we skip that.
        // Create the collection that we need to return from the stub.
        List<Book> books = List.of(
                new Book("1234", "Mockito in Action", 500, LocalDate.now()),
                new Book("1235", "JUnit5 in Action", 400, LocalDate.now())
        );

        // Stub the repository in the Assert step.
        given(bookRepository.findNewBooks(7)).willReturn(books);

        //////////////// Act Step (When)

        // Invoke the actual method on the Class under test. This will be done in the Act step.
        List<Book> newBooks = bookService.getNewBooksWithAppliedDiscount(10, 7);

        //////////////// Assert Step (Then)

        // Use BDD style assertions provided by AssertJ library.

        // Check that the collection is not null
        then(newBooks).isNotNull();

        // Verify that the collection size is 2.
        then(newBooks.size()).isEqualTo(2);

        // Verify that 10% discount is applied to both the books in the returned list.
        then(newBooks.get(0).getPrice()).isEqualTo(450);
        then(newBooks.get(1).getPrice()).isEqualTo(360);

    }
}