package com.namesorter.factory;


import com.namesorter.exception.InvalidInputException;
import com.namesorter.model.Name;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class NameFactoryTest {

    @InjectMocks
    private NameFactory sut;

    @Test
    public void given_unsplitNameString_should_returnSplitName(){
        Name splitNames = sut.fromString("Test Name Jimmy");
        Name controlNames = new Name("Test Name", "Jimmy");
        assertThat(splitNames.compareTo(controlNames)).isZero();
    }

            //splits string up
    @Test (expected = InvalidInputException.class)
    public void given_tooManyNamesInString_should_throwInvalidInputException(){sut.fromString("This Line Has Too Many Names");
    }
}
