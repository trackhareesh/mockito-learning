package com.myspringprojects.rnd.mockitolearning.testdoubles.dummy;

import java.util.Collection;

public interface BookRepository {

    void save(Book book);

    Collection<Book> findAll();

}
