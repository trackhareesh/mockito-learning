package com.myspringprojects.rnd.mockitolearning.stubbing;

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

@ExtendWith(MockitoExtension.class)
@DisplayName("Total Cost Test By Stubbing")
class BookServiceTest {

    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    @Test
    @DisplayName("Stub Using when and thenReturns")
    void testCalculateTotalCostOfBooks() {

        Mockito.when(bookRepository.findByBookId("1234")).thenReturn(
                new Book("1234", "Testing with JUnit 5", 400, LocalDate.now())
        );
        Mockito.when(bookRepository.findByBookId("1235")).thenReturn(
                new Book("1235", "Testing with Mockito 3", 500, LocalDate.now())
        );

        int totalCost = bookService.calculateTotalCost(List.of("1234", "1235"));

        assertEquals(900, totalCost);

    }

    @Test
    @DisplayName("Stub Using doReturn and when")
    void testCalculateTotalCostOfBooksUsingDoReturn() {

        Book book1 = new Book("1234", "Testing with JUnit 5", 400, LocalDate.now());
        Book book2 = new Book("1235", "Testing with Mockito 3", 500, LocalDate.now());

        Mockito.doReturn(book1).when(bookRepository).findByBookId("1234");
        Mockito.doReturn(book2).when(bookRepository).findByBookId("1235");

        int totalCost = bookService.calculateTotalCost(List.of("1234", "1235"));

        assertEquals(900, totalCost);

    }
}