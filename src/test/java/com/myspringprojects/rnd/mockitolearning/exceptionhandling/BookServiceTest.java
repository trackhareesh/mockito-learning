package com.myspringprojects.rnd.mockitolearning.exceptionhandling;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Exception Handling")
class BookServiceTest {

    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    @Test
    @DisplayName("Exception Handling for Non Void Method - Total Price of Books")
    void testGetTotalPriceOfBooksForException() throws SQLException {
        // Stub the book repository findAllBooks method to throw an SQLException.
        Mockito.when(bookRepository.findAllBooks()).thenThrow(SQLException.class);

        // Verify that the service method throws an ApplicationException when it obtains
        // an SQLException from the repository.
        assertThrows(ApplicationException.class, () -> bookService.getTotalPriceOfBooks());
    }

    @Test
    @DisplayName("Exception Handling for Non Void Method BDD Style - Total Price of Books")
    void testGetTotalPriceOfBooksForExceptionBDDStyle() throws SQLException {

        // Stub the book repository findAllBooks method to throw an SQLException using
        // BDDMockito class.
        BDDMockito.given(bookRepository.findAllBooks()).willThrow(SQLException.class);

        // Verify that the service method throws an ApplicationException when it obtains
        // an SQLException from the repository.
        assertThrows(ApplicationException.class, () -> bookService.getTotalPriceOfBooks());
    }

    @Test
    @DisplayName("Exception Handling for Void Method - Save Book")
    void testSaveBookForException() throws SQLException {

        Book book = new Book("1234", "JUnit 5 in Action", 500, LocalDate.now());

        // Stub the book repository findAllBooks method to throw an SQLException.
        Mockito.doThrow(SQLException.class).when(bookRepository).save(book);

        // Verify that the service method throws an ApplicationException when it obtains
        // an SQLException from the repository.
        assertThrows(ApplicationException.class, () -> bookService.addBook(book));
    }

}