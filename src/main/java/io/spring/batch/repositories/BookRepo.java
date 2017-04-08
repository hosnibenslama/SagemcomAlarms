package io.spring.batch.repositories;

import io.spring.batch.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Hosni on 09/03/2017.
 */
@Repository
public interface BookRepo extends JpaRepository<Book, Integer> {


/*

    public Book findByTitle(String title);

    public  List<Book> findAllByTitle(String title);

    //Relational Operators
    public  List<Book> findByTitleLike(String title);

    public  List<Book> findByTitleContaining(String title);

    public  List<Book> findByTitleStartingWith(String title);

    public  List<Book> findByTitleEndingWith(String title);

    public  List<Book> findByTitleIgnoreCase(String title);

    public  List<Book> findByPageCountEquals(int pageCount);

    public  List<Book> findByPageCountGreaterThan(int pageCount);

    public  List<Book> findByPageCountGreaterThanEqual(int pageCount);

    public  List<Book> findByPageCountLessThan(int pageCount);

    public  List<Book> findByPageCountBetween(int min,int max);

    //Logical operators
    public  List<Book> findByTitleContainingOrTitleContaining(String title, String title2);

    public  List<Book> findByTitleContainingAndTitleContaining(String title, String title2);

    public  List<Book> findByTitleContainingAndPageCountGreaterThan(String title, int pageCount);

    public  List<Book> findByTitleNot(String title);

    //Date Comparisons
    public  List<Book> findByPublishDate(Date date);

    public  List<Book> findByPublishDateAfter(Date date);

    public  List<Book> findByPublishDateBefore(Date date);

    public  List<Book> findByPublishDateBetween(Date date, Date date2);

    //Ordering results
    public  List<Book> findByTitleContainingOrderByTitleAsc(String title);

    public  List<Book> findByTitleContainingOrderByTitleDesc(String title);

    public  List<Book> findByOrderByPageCountDesc();

    public  List<Book> findByOrderByBookIdDesc();

    //Limiting Query results
    public  List<Book> findTopByOrderByPageCountDesc();

    public  List<Book> findFirstByOrderByPageCountAsc();

    public  List<Book> findFirst5ByOrderByPriceDesc(); //return the top 5 priciest books

    public  List<Book> findTop5ByTitleOrderByPriceAsc(String title);

    //Query techniques

    @Query("Select b from Book b")
    public   List<Book> queryOne();

    @Query("Select b from Book b where b.pageCount > ?1 ")
    public   List<Book> queryTwo(int pageCount);

    @Query("Select b from Book b where b.title = :title ")
    public   List<Book> queryThree(@Param("title") String title);

    @Query(value = "Select * from Book where title = :title", nativeQuery = true)
    public   List<Book> queryFour(@Param("title") String title);

    @Query(value = "Select * from Book limit :first"+","+":second", nativeQuery = true)
    public   List<Book> queryFive(@Param("first") int first,@Param("second") int second);


    //Paging
    public  Page<Book> findByPageCountGreaterThan( int pageCount, Pageable pageable);

    public  Page<Book> findAll( Pageable pageable);



*/


}
