package io.spring.batch.services;

import io.spring.batch.domain.BookCategory;

import java.util.List;

/**
 * Created by Hosni on 14/03/2017.
 */

public interface BookCategoryService {

    public List<BookCategory> findAll();

    public void save(BookCategory book);

}
