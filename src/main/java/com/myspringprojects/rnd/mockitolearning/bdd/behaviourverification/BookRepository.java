package com.myspringprojects.rnd.mockitolearning.bdd.behaviourverification;

public interface BookRepository {

    void save(Book book);

    Book findById(String bookId);
}
