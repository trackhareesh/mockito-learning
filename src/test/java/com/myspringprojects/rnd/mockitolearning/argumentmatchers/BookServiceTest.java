package com.myspringprojects.rnd.mockitolearning.argumentmatchers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Argument Matchers")
class BookServiceTest {

    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    @Test
    @DisplayName("Type Argument Matchers Test - Update Price")
    void testUpdatePriceWithSamePrice() {

        Book book1 = new Book("1234", "JUnit 5 in Action", 600, LocalDate.now());
        Book book2 = new Book("1234", "JUnit 5 in Action", 700, LocalDate.now());

        when(bookRepository.findById(any(String.class))).thenReturn(book1);

        bookService.updatePrice("1234", 700);

        // verify that save is called exactly once.
        verify(bookRepository).save(book2);

    }

    @Test
    @DisplayName("Type Argument Matchers - Get Book By Title And Published Date")
    void testGetBookByTitleAndPublishedDate() {
        Book book = new Book("1234", "JUnit 5 in Action", 600, LocalDate.now());
        when(bookRepository.findByTitleAndPublishedDate(eq("JUnit 5 in Action"), any())).thenReturn(book);
        Book actualBook = bookService.getBookByTitleAndPublishedDate("JUnit 5 in Action", LocalDate.now());
        assertEquals("JUnit 5 in Action", actualBook.getTitle());
    }

    @Test
    @DisplayName("Specific Type Argument Matchers - Get Book By Title, Price and IsDigital")
    void testGetBookByTitlePriceAndIsDigital() {
        Book book = new Book("1234", "JUnit 5 in Action", 600, LocalDate.now(), true);
        when(bookRepository.findByTitlePriceAndIsDigital(anyString(), anyInt(), anyBoolean())).thenReturn(book);
        Book actualBook = bookService.getBookByTitlePriceAndIsDigital("JUnit 5 in Action", 600, true);
        assertEquals("JUnit 5 in Action", actualBook.getTitle());
    }
}