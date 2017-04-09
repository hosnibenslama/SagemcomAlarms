package io.spring.batch.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hosni on 07/04/2017.
 */


@XmlRootElement(name = "lists")
public class ModelFile {

    //List of books

    private List<Book> listBook = new ArrayList<Book>();
    //List of book categories

    private List<BookCategory> listBookCategory = new ArrayList<BookCategory>();

    public ModelFile(){

    }


    @XmlElementWrapper(name = "books")
    @XmlElement(name = "book")
    public List<Book> getListBook() {
        return listBook;
    }

    public void setListBook(List<Book> listBook) {
        this.listBook = listBook;
    }

    @XmlElementWrapper(name = "booksCategories")
    @XmlElement(name = "bookCategory")
    public List<BookCategory> getListBookCategory() {
        return listBookCategory;
    }

    public void setListBookCategory(List<BookCategory> listBookCategory) {
        this.listBookCategory = listBookCategory;
    }

    @XmlElementWrapper(name = "bookThemes")
    @XmlElement(name = "bookTheme")
    public List<Theme> getListTheme() {
        return listTheme;
    }

    public void setListTheme(List<Theme> listTheme) {
        this.listTheme = listTheme;
    }

    @XmlElementWrapper(name = "booksAuteurs")
    @XmlElement(name = "bookAuteur")
    public List<Auteur> getListAuteur() {
        return listAuteur;
    }

    public void setListAuteur(List<Auteur> listAuteur) {
        this.listAuteur = listAuteur;
    }

    //List of book Themes

    private List<Theme> listTheme = new ArrayList<Theme>();


    private List<Auteur> listAuteur = new ArrayList<Auteur>();


}
