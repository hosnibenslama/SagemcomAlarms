package io.spring.batch.services;

import io.spring.batch.domain.Book;

import java.util.List;

/**
 * Created by Hosni on 13/03/2017.
 */

public interface BookService {

    public List<Book> findAll();

    public void save(Book book);

}
