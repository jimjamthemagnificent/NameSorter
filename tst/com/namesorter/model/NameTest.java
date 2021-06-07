package com.namesorter.model;


import com.namesorter.io.FileNameReader;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class NameTest {

    @InjectMocks
    private Name sut;

    @Test
    public void given_multipleNameStrings_should_returnSingleString() {
        sut = new Name("Test", "Name");
        assertThat(sut.toString().equals("Test Name"));
    }

    @Test
    public void given_calledWithLesserSurname_Should_returnPositive() {
        sut = new Name("A", "Boblin");
        Name lesserName = new Name("A", "Doblin");
        assertThat(sut.compareTo(lesserName)).isEqualTo(1);
    }

    @Test
    public void given_calledWithSameSurnameAndLesserGivenName_Should_returnPositive() {
        sut = new Name("A", "Boblin");
        Name lesserName = new Name("B", "Boblin");
        assertThat(sut.compareTo(lesserName)).isEqualTo(1);
    }

    @Test
    public void given_calledWithGreaterSurname_should_returnNegative () {
        sut = new Name("A", "Boblin");
        Name greaterName = new Name("A", "Aoblin");
        assertThat(sut.compareTo(greaterName)).isEqualTo(-1);
    }

    @Test
    public void given_calledWithSameSurnameAndGreaterGivenname_should_returnNegative () {
        sut = new Name("B", "Boblin");
        Name greaterName = new Name("A", "Boblin");
        assertThat(sut.compareTo(greaterName)).isEqualTo(-1);
    }

    @Test
    public void given_identicalNames_should_returnEqual () {
        sut = new Name("A", "Boblin");
        Name equalName = new Name("A", "Boblin");
        assertThat(sut.compareTo(equalName)).isZero();
    }
}
