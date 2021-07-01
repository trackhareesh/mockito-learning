package com.myspringprojects.rnd.mockitolearning.bdd.behaviourverification;


import com.myspringprojects.rnd.mockitolearning.behaviour.verification.BookRequest;

public class BookService {

    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void updatePrice(String bookId, int updatedPrice) {
        // Load the book using the bookId
        Book book = bookRepository.findById(bookId);

        // Update the price in the loaded book
        book.setPrice(updatedPrice);

        // Save the updated book.
        bookRepository.save(book);
    }
}
