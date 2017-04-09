package io.spring.batch.domain;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Hosni on 15/03/2017.
 */

@Entity
public class Auteur {

    @Id
    private int id;

    private String name;

    private List<Book> books;

    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "auteur", cascade = CascadeType.ALL)
    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
