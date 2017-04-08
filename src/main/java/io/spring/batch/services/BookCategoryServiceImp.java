package io.spring.batch.services;

import io.spring.batch.domain.BookCategory;
import io.spring.batch.repositories.BookCategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Hosni on 14/03/2017.
 */
@Service
public class BookCategoryServiceImp implements BookCategoryService {

    @Autowired
    BookCategoryRepo bookCategoryRepo;


    @Override
    public List<BookCategory> findAll() {
        return bookCategoryRepo.findAll();
    }

    @Override
    public void save(BookCategory book) {

        bookCategoryRepo.save(book);

    }
}
