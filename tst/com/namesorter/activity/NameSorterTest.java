package com.namesorter.activity;

import com.namesorter.exception.InvalidInputException;
import com.namesorter.io.FileNameReader;
import com.namesorter.io.FileNameSaver;
import com.namesorter.model.Name;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class NameSorterTest {

    private static final String FILE_PATH = "TesterPath";

    @Mock
    private FileNameReader fileNameReader;

    @Mock
    private FileNameSaver fileNameSaver;

    @InjectMocks
    private NameSorter sut;

    @Test
    public void given_threeUnsortedNames_should_returnSortedNameList() {
        Name name01 = new Name("J J", "Abrams");
        Name name02 = new Name("A A", "Milne");
        Name name03 = new Name("J D", "Milne");
        List<Name> unSortedList = new ArrayList<>();
        unSortedList.add(name03);
        unSortedList.add(name01);
        unSortedList.add(name02);
        when(fileNameReader.readNamesFromFile(FILE_PATH)).thenReturn(unSortedList);

        List<Name> returnedNameList = sut.getSortedNamesFromFile(FILE_PATH);

        assertThat(returnedNameList )
                .hasSize(3)
                .containsExactly(name01, name02, name03);
        verify(fileNameSaver).saveNamesToFile(returnedNameList);
    }

    @Test(expected = InvalidInputException.class)
    public void given_readerThrowsException_should_notSave() {
        doThrow(new InvalidInputException("uh oh")).when(fileNameReader).readNamesFromFile(FILE_PATH);
        try {
            sut.getSortedNamesFromFile(FILE_PATH);
        }
        finally {
            verify(fileNameSaver, never()).saveNamesToFile(any());
        }
    }
}
