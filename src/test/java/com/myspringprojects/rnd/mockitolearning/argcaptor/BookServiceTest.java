package com.myspringprojects.rnd.mockitolearning.argcaptor;

import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Argument Capture")
class BookServiceTest {

    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    @Captor
    private ArgumentCaptor<Book> argumentCaptor;

    @Test
    @DisplayName("Capture Args when a method is executed - Add Book")
    void testAddBook() {

        // Create a new BookRequest to use with addBook method.
        BookRequest bookRequest = new BookRequest("Mockito in Action", 500, LocalDate.now());

        // Instantiate an ArgumentCaptor of type Book. This can be used to capture any book variable
        // passed in one of the verification steps.
        ArgumentCaptor<Book> bookArgumentCaptor = ArgumentCaptor.forClass(Book.class);

        // Invoke the actual service method.
        bookService.addBook(bookRequest);

        // Invoke the verify method to check if the save method is executed exactly once.
        // But, instead of providing an instance of Book to check the parameter, we disable the parameter check
        // here. Instead we pass the ArgumentCaptor instance to inform Mockito to capture the Book parameter
        // with which the save method is invoked, so that we can do further assertions down the line, with this
        // captured object. Note that, when we use an ArgumentCaptor instead of an actual value, we disable the
        // parameter check, and instead enable parameter capture.
        Mockito.verify(bookRepository).save(bookArgumentCaptor.capture());

        // Get the captured parameter during the verification.
        Book book = bookArgumentCaptor.getValue();

        // Perform assertions on the state of the captured parameter.
        assertEquals("Mockito in Action", book.getTitle());
        assertEquals(500, book.getPrice());

    }

    @Test
    @DisplayName("Capture Args using Captor Annotation - Add Book")
    void testAddBookUsingCaptorAnnotation() {

        // Create a new BookRequest to use with addBook method.
        BookRequest bookRequest = new BookRequest("Mockito in Action", 500, LocalDate.now());

        // Invoke the actual service method.
        bookService.addBook(bookRequest);

        // Invoke the verify method to check if the save method is executed exactly once.
        // But, instead of providing an instance of Book to check the parameter, we disable the parameter check
        // here. Instead we pass the ArgumentCaptor instance to inform Mockito to capture the Book parameter
        // with which the save method is invoked, so that we can do further assertions down the line, with this
        // captured object. Note that, when we use an ArgumentCaptor instead of an actual value, we disable the
        // parameter check, and instead enable parameter capture.
        Mockito.verify(bookRepository).save(argumentCaptor.capture());

        // Get the captured parameter during the verification.
        Book book = argumentCaptor.getValue();

        // Perform assertions on the state of the captured parameter.
        assertEquals("Mockito in Action", book.getTitle());
        assertEquals(500, book.getPrice());

    }

}