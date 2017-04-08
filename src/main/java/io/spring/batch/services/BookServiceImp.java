package io.spring.batch.services;

import io.spring.batch.domain.Book;
import io.spring.batch.repositories.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Hosni on 13/03/2017.
 */
@Service
public class BookServiceImp implements  BookService{


    @Autowired
    BookRepo bookRepo;


    @Override
    public List<Book> findAll() {

      return   bookRepo.findAll();

    }

    @Override
    public void save(Book book) {
        bookRepo.save(book);
    }
}
