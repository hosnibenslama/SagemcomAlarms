package io.spring.batch.reader;

/**
 * Created by Hosni on 07/04/2017.
 */

import io.spring.batch.domain.*;
import io.spring.batch.services.AuteurService;
import io.spring.batch.services.BookCategoryService;
import io.spring.batch.services.BookService;
import io.spring.batch.services.ThemeService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component("modelFile")
@Getter
@Setter
public class ModelFileItemReader implements ItemReader<ModelFile> {

    @Autowired
    public BookService bookService;

    @Autowired
    public BookCategoryService bookCategoryService;

    @Autowired
    public AuteurService auteurService;

    @Autowired
    public ThemeService themeService;


    private int nextItemIndex;

    private ModelFile modelFile = new ModelFile();

    private List<ModelFile> modelFileData;



    public void initialize() {

        List<Book> listBook = bookService.findAll();
        List<BookCategory> listBookCategory= bookCategoryService.findAll();
        List<Theme> listTheme = themeService.findAll();
        List<Auteur> listAuteur = auteurService.findAll();

        modelFile.setListAuteur(listAuteur);
        modelFile.setListBook(listBook);
        modelFile.setListBookCategory(listBookCategory);
        modelFile.setListTheme(listTheme);



        modelFileData = Collections.unmodifiableList(Arrays.asList(modelFile));
        nextItemIndex = 0;
    }


    public ModelFile read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        ModelFile modelFile = null;

        if ( nextItemIndex < modelFileData.size()) {
            modelFile = modelFileData.get(nextItemIndex);
            nextItemIndex++;
        }

        return modelFile;
    }

}