package com.myspringprojects.rnd.mockitolearning.stubbing;

import java.util.List;

public interface BookRepository {

    List<Book> findNewBooks(int days);

    Book findByBookId(String bookId);

    void save(Book book);
}
