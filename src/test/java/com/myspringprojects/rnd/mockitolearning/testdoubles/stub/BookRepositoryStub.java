package com.myspringprojects.rnd.mockitolearning.testdoubles.stub;

import java.time.LocalDate;
import java.util.List;

public class BookRepositoryStub implements BookRepository {

    @Override
    public List<Book> findNewBooks(int days) {
        return List.of(
                new Book("1234", "Mockito in Action", 500, LocalDate.now()),
                new Book("1235", "JUnit5 in Action", 400, LocalDate.now())
        );
    }
}
