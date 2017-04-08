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

public class InMemoryStudentReader implements ItemReader<ModelFile> {

    private int nextStudentIndex;
    private List<ModelFile> studentData;

    public InMemoryStudentReader() {
        initialize();
    }

    private void initialize() {

        ModelFile modelFile =new ModelFile();

        modelFile.setExample("EXAMPLE1");


        studentData = Collections.unmodifiableList(Arrays.asList(modelFile));
        nextStudentIndex = 0;
    }


    @Override
    public ModelFile read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        ModelFile nextStudent = null;

        if (nextStudentIndex < studentData.size()) {
            nextStudent = studentData.get(nextStudentIndex);
            nextStudentIndex++;
        }

        return nextStudent;
    }

}