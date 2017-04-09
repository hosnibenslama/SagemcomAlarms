package io.spring.batch.domain;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;
import java.util.List;

/**
 * Created by Hosni on 15/03/2017.
 */
@Entity
public class Theme {


    private int id;

    private String name;

    private List<BookCategory> bookCategories;

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

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "theme", cascade = CascadeType.ALL)
    @XmlTransient
    public List<BookCategory> getBookCategories() {
        return bookCategories;
    }

    public void setBookCategories(List<BookCategory> bookCategories) {
        this.bookCategories = bookCategories;
    }
}
