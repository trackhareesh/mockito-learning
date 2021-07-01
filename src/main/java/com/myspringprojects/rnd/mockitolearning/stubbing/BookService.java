package com.myspringprojects.rnd.mockitolearning.stubbing;

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

    /**
     *
     *  Method that returns the total amount of all the books specified
     * by the list of book ids.
     *
     * @param bookIds The list of bookIds.
     *
     * @return The total price of all the books.
     *
     */
    public int calculateTotalCost(List<String> bookIds) {

        // Load each book using the bookId, from the BookRepository.
        List<Book> books = bookIds.stream()
                .map(bookId -> bookRepository.findByBookId(bookId))
                .collect(Collectors.toList());

        // Return the sum of prices of all the books in the collection.
        return books.stream().mapToInt(Book::getPrice).sum();
    }

    /**
     * Method to test stubbing of a void method using mockito.
     *
     * @param book The book to be saved.
     */
    public void addBook(Book book) {
        bookRepository.save(book);
    }

    /**
     * Add a book using a BookRequest object. The method internally creates a Book
     * instance and uses the repository to save it.
     *
     * @param bookRequest The BookRequest to be used to create the book to be saved.
     */
    public void addBook(BookRequest bookRequest) {
        // Create a book instance from the bookRequest instance.
        Book book = new Book(null, bookRequest.getTitle(),
                bookRequest.getPrice(), bookRequest.getPublishedDate());

        // Now call the save method, so that the repository save uses a different
        // object than what is passed to the service, from the test case.
        bookRepository.save(book);
    }

}
