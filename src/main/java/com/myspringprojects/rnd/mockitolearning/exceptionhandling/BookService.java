package com.myspringprojects.rnd.mockitolearning.exceptionhandling;


import java.sql.SQLException;
import java.util.List;

public class BookService {

    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public int getTotalPriceOfBooks() {
        try {
            List<Book> books = bookRepository.findAllBooks();
            return books.stream().mapToInt(Book::getPrice).sum();
        } catch (SQLException throwables) {
            // Log and throw the exception
            throw new ApplicationException("Unable to read from DB. Reason - " + throwables.getMessage());
        }
    }

}
