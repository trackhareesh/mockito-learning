package com.myspringprojects.rnd.mockitolearning.exceptionhandling;

import java.sql.SQLException;
import java.util.List;

public interface BookRepository {

    List<Book> findAllBooks() throws SQLException;

    void save(Book book) throws SQLException;
}
