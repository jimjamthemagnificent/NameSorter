package com.namesorter.io;

import com.namesorter.exception.InvalidInputException;
import com.namesorter.factory.NameFactory;
import com.namesorter.model.Name;
import com.namesorter.test.data.TestingConstants;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.File;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FileNameReaderTest {

    @Mock
    private NameFactory nameFactory;

    @InjectMocks
    private FileNameReader sut;

    @Test
    public void given_realFile_should_callFactoryForEachLineAndReturn() {
        assertTrue(new File(TestingConstants.TEST_NAMES_FILE_PATH).exists());

        Name name01 = mock(Name.class);
        Name name02 = mock(Name.class);
        Name name03 = mock(Name.class);
        Name name04 = mock(Name.class);

        when(nameFactory.fromString(TestingConstants.NAME_01)).thenReturn(name01);
        when(nameFactory.fromString(TestingConstants.NAME_02)).thenReturn(name02);
        when(nameFactory.fromString(TestingConstants.NAME_03)).thenReturn(name03);
        when(nameFactory.fromString(TestingConstants.NAME_04)).thenReturn(name04);

        List<Name> returnedNamesList = sut.readNamesFromFile(TestingConstants.TEST_NAMES_FILE_PATH);
        assertThat(returnedNamesList).containsOnly(name01, name02, name03, name04);
    }

    @Test (expected = InvalidInputException.class)
    public void given_badFilePath_should_throwInvalidInput() {
        sut.readNamesFromFile("this/is/a/nonsense/filepath");
    }
}
