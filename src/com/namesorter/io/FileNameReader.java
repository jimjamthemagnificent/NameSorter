package com.namesorter.io;

import com.namesorter.exception.InvalidInputException;
import com.namesorter.factory.NameFactory;
import com.namesorter.model.Name;

import javax.inject.Inject;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileNameReader {
    private final NameFactory nameFactory;

    @Inject
    public FileNameReader(NameFactory nameFactory) {
        this.nameFactory = nameFactory;
    }

    public List<Name> readNamesFromFile(String filePath){
        File unsortedNameFile = new File(filePath);
        List<Name> namesList = new ArrayList<>();
        try(Scanner nameReader = new Scanner(unsortedNameFile)){
            while (nameReader.hasNextLine()){
                Name currentName = nameFactory.fromString(nameReader.nextLine());
                namesList.add(currentName);
            }
        } catch (FileNotFoundException e) {
            throw new InvalidInputException("Unable to find file " + filePath, e);
        }
        return namesList;
    }
}
