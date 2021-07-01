package com.myspringprojects.rnd.mockitolearning.spies;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Spies in Mockito")
class BookManagerTest {

    @InjectMocks
    private BookManager bookManager;

    @Spy
    private BookService bookService;

    @Test
    @DisplayName("Test Mockito Spy - Apply Discount On Book")
    public void testApplyDiscountOnBook() {
        // Here we need to test the behaviour of the applyDiscountOnBook method of the BookManager. In order
        // to do that, we use a spy of the BookService instance. On this spy, we stub the implementation of the
        // findBook() method, but use the actual implementation of the getAppliedDiscount method.

        // Create a book instance.
        Book book = new Book("1234", " Mockito in Action", 500, LocalDate.now());

        // Stub the getBook method in BookService class.
        Mockito.doReturn(book).when(bookService).findBook("1234");

        // Don't use the 'when' syntax as shown below, as this will execute the actual method even though
        // finally it returns the specified object. This behaviour is unlike Mock objects.
        // Mockito.when(bookService.findBook("1234")).thenReturn(book);

        // Invoke the applyDiscountOnBook method on the spy object. Note that we did not stub the getAppliedDiscount()
        // method on the BookService. Hence, the actual implementation of that method will be invoked.
        int discountedPrice = bookManager.applyDiscountOnBook("1234", 10);

        // Verify that the correct price is calculated and returned.
        assertEquals(450, discountedPrice);
    }
}