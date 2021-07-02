package com.myspringprojects.rnd.mockitolearning.argumentmatchers;

import java.time.LocalDate;
import java.util.List;

public interface BookRepository {

    void save(Book book);

    Book findById(String bookId);

    Book findByTitleAndPublishedDate(String title, LocalDate publishedDate);

    Book findByTitlePriceAndIsDigital(String title, int price, boolean isDigital);

    void saveAll(List<Book> books);
}
