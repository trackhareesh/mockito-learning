package com.myspringprojects.rnd.mockitolearning.behaviour.verification;


import java.util.List;
import java.util.stream.Collectors;

public class BookService {

    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    /**
     * Method to test stubbing of a void method using mockito.
     *
     * @param book The book to be saved.
     */
    public void addBook(Book book) {

        // Introducing a business logic here by not saving the book if the
        // price of the book is less than 500. Our purpose is to test if this logic
        // is getting executed by using Behaviour verification methods provided by Mockito.
        if (book.getPrice() <= 500) {
            return;
        }

        // If the price of the book is greater than 500, save it.
        bookRepository.save(book);
    }


    /**
     * Add a book using a BookRequest object. The method internally creates a Book
     * instance and uses the repository to save it.
     *
     * @param bookRequest The BookRequest to be used to create the book to be saved.
     */
    public void addBook(BookRequest bookRequest) {

        // Introducing complex behaviour in the service method. Our purpose is to do the behaviour
        // verification using Mockito in different scenarios.
        if (bookRequest.getPrice() <= 500) {
            return;
        }

        // Create a book instance from the bookRequest instance.
        Book book = new Book(null, bookRequest.getTitle(),
                bookRequest.getPrice(), bookRequest.getPublishedDate());

        // Now call the save method, so that the repository save uses a different
        // object than what is passed to the service, from the test case.
        bookRepository.save(book);
    }

    public void updatePrice(String bookId, int updatedPrice) {
        // If the bookId is null, don't do anything.
        if (bookId == null) {
            return;
        }
        Book book = bookRepository.findById(bookId);

        // If the updated price is same as the existing price, return.
        if (book.getPrice() == updatedPrice) {
            return;
        }
        book.setPrice(updatedPrice);
        bookRepository.save(book);
    }
}
