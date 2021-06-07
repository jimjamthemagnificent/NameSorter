package com.namesorter.io;

import com.namesorter.model.Name;
import com.namesorter.test.data.TestingConstants;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static junit.framework.TestCase.fail;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FileNameSaverTest {
    private FileNameSaver sut;

    @Before
    public void setupSut() {
        sut = new FileNameSaver(TestingConstants.TEST_SAVE_FILE_PATH);
    }

    @After
    public void cleanup() {
        File savedFile = new File(TestingConstants.TEST_SAVE_FILE_PATH);
        if (savedFile.exists()) {
            savedFile.delete();
        }
    }

    @Test
    public void given_listOfNames_should_save() {
        Name name01 = mock(Name.class);
        Name name02 = mock(Name.class);
        Name name03 = mock(Name.class);
        Name name04 = mock(Name.class);

        when(name01.toString()).thenReturn(TestingConstants.NAME_01);
        when(name02.toString()).thenReturn(TestingConstants.NAME_02);
        when(name03.toString()).thenReturn(TestingConstants.NAME_03);
        when(name04.toString()).thenReturn(TestingConstants.NAME_04);

        List<Name> nameList = new ArrayList<>();
        nameList.add(name01);
        nameList.add(name02);
        nameList.add(name03);
        nameList.add(name04);

        sut.saveNamesToFile(nameList);

        File savedFile = new File(TestingConstants.TEST_SAVE_FILE_PATH);
        try(Scanner scanner = new Scanner(savedFile)){
            assertThat(scanner.nextLine()).isEqualTo(TestingConstants.NAME_01);
            assertThat(scanner.nextLine()).isEqualTo(TestingConstants.NAME_02);
            assertThat(scanner.nextLine()).isEqualTo(TestingConstants.NAME_03);
            assertThat(scanner.nextLine()).isEqualTo(TestingConstants.NAME_04);
        } catch (FileNotFoundException e) {
            fail("File was not saved.");
        }
    }
}
