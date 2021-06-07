package com.namesorter.factory;

import com.namesorter.exception.InvalidInputException;
import com.namesorter.model.Name;

import java.util.Arrays;

public class NameFactory {
    public Name fromString(String rawString){
        String[] allNames = rawString.split(" ");
        if (allNames.length < 2 || allNames.length > 4) {
            throw new InvalidInputException(rawString + " is not a valid name.");
        }
        String surname = allNames[allNames.length - 1];
        String[] givenNamesArray = Arrays.copyOfRange(allNames, 0, allNames.length - 1);
        String givenNames = String.join(" ", givenNamesArray);

        return new Name(givenNames, surname);
    }
}
