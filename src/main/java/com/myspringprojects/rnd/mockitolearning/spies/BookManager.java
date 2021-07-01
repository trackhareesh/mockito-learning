package com.myspringprojects.rnd.mockitolearning.spies;

public class BookManager {

    private BookService bookService;

    public BookManager(BookService bookService) {
        this.bookService = bookService;
    }

    /**
     * Here the behaviour of this method is dependent on 2 other methods of the BookService. The findBook method
     * will fetch the book from the DB, while the getAppliedDiscount method will perform business calculations on
     * the fetched book. Here we can use a spy to test the behaviour of this method. In the spy object, we can
     * stub the implementation of the DB fetch call, but let the actual call to the getAppliedDiscount happen on the
     * spy.
     *
     * @param bookId The bookId of the Book to be fetched.
     * @param discountRate The rate of discount to be applied.
     *
     * @return The net price after applying the calculated discount.
     */
    public int applyDiscountOnBook(String bookId, int discountRate) {
        Book book = bookService.findBook(bookId);
        return bookService.getAppliedDiscount(book, discountRate);
    }
}
