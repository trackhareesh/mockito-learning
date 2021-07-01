package com.myspringprojects.rnd.mockitolearning.argcaptor;

public interface BookRepository {

    void save(Book book);

    Book findById(String bookId);
}
