package io.spring.batch.repositories;

import io.spring.batch.domain.BookCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Hosni on 14/03/2017.
 */
@Repository
public interface BookCategoryRepo extends JpaRepository<BookCategory, Integer> {


}
