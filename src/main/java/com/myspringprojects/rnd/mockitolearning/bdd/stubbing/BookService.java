package com.myspringprojects.rnd.mockitolearning.bdd.stubbing;

import java.util.List;
import java.util.stream.Collectors;

public class BookService {

    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    /**
     * Apply the specified discount to all books that were published in the last 'days' number of days.
     *
     * @param discountRate The rate of discount to apply.
     * @param days         the number of days backwards to check for published books.
     * @return The list of books with the specified discount applied.
     */
    public List<Book> getNewBooksWithAppliedDiscount(int discountRate, int days) {

        // Load all books published within the last 'days' number of days.
        List<Book> newBooks = bookRepository.findNewBooks(days);

        // Return a list of books where the price of each book is calculated by applying
        // a discount based on the specified 'discountRate'.
        return newBooks.stream()
                .map(book -> new Book(
                        book.getBookId(),
                        book.getTitle(),
                        book.getPrice() - (book.getPrice() * discountRate / 100),
                        book.getPublishedDate())
                ).collect(Collectors.toList());
    }

}
