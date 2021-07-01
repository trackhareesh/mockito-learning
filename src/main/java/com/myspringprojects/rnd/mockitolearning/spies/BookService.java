package com.myspringprojects.rnd.mockitolearning.spies;

public class BookService {

    public Book findBook(String bookId) {
        // Get the book from the DB.
        throw new RuntimeException("Method not implemented!!");
    }

    public int getAppliedDiscount(Book book, int discountRate) {
        int price = book.getPrice();
        return price - (price * discountRate / 100);
    }
}
