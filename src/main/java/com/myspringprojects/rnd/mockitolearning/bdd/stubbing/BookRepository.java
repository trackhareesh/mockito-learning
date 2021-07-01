package com.myspringprojects.rnd.mockitolearning.bdd.stubbing;

import java.util.List;

public interface BookRepository {

    List<Book> findNewBooks(int days);
}
