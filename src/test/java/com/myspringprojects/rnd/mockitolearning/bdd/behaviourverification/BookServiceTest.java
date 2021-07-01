package com.myspringprojects.rnd.mockitolearning.bdd.behaviourverification;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("BDD Style Behaviour Verification")
class BookServiceTest {

    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    @Test
    @DisplayName("Given a book, When update price is called with new price, Then Book price is updated")
    void testUpdatePriceBDDStyle() {

        // Arrange step.

        // In the arrage step, we create the required objects and stub the required methods on
        // external dependencies using BDDMockito methods.
        Book book = new Book("1234", "JUnit 5 in Action", 600, LocalDate.now());
        BDDMockito.given(bookRepository.findById("1234")).willReturn(book);

        // Act step

        // In the act step, we call the method to be tested on the class under test.
        bookService.updatePrice("1234", 500);

        // Assert Step.

        // In the Assert step, we verify the interactions using the BDDMockito then/should methods.
        // This makes it more readable than the conventional 'verify' methods.
        BDDMockito.then(bookRepository).should().save(book);
    }
}