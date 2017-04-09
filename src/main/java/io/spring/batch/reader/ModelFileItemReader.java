package io.spring.batch.reader;

/**
 * Created by Hosni on 07/04/2017.
 */

import io.spring.batch.domain.ModelFile;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class ModelFileItemReader implements ItemReader<ModelFile> {


    private int nextItemIndex;

    private ModelFile modelFile;

    private List<ModelFile> modelFileData;

    public ModelFile getModelFile() {
        return modelFile;
    }

    public void setModelFile(ModelFile modelFile) {
        this.modelFile = modelFile;
    }

    public List<ModelFile> getModelFileData() {
        return modelFileData;
    }

    public void setModelFileData(List<ModelFile> modelFileData) {
        this.modelFileData = modelFileData;
    }

    public ModelFileItemReader() {

    }

    public void initialize() {


        modelFileData = Collections.unmodifiableList(Arrays.asList(modelFile));
        nextItemIndex = 0;
    }


    @Override
    public ModelFile read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        ModelFile modelFile = null;

        if ( nextItemIndex < modelFileData.size()) {
            modelFile = modelFileData.get(nextItemIndex);
            nextItemIndex++;
        }

        return modelFile;
    }

}