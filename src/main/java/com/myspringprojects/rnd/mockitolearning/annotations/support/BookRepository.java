package com.myspringprojects.rnd.mockitolearning.annotations.support;

import java.util.List;

public interface BookRepository {

    List<Book> findNewBooks(int days);
}
