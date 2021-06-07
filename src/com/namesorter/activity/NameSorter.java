package com.namesorter.activity;

import com.namesorter.io.FileNameReader;
import com.namesorter.io.FileNameSaver;
import com.namesorter.model.Name;

import javax.inject.Inject;
import java.util.List;

public class NameSorter {

    private final FileNameReader fileNameReader;
    private final FileNameSaver fileNameSaver;

    @Inject
    public NameSorter(FileNameReader fileNameReader, FileNameSaver fileNameSaver){
        this.fileNameReader = fileNameReader;
        this.fileNameSaver = fileNameSaver;
    }

    public List<Name> getSortedNamesFromFile(String filePath){
        List<Name> namesList = fileNameReader.readNamesFromFile(filePath);
        namesList.sort(Name::compareTo);
        fileNameSaver.saveNamesToFile(namesList);
        return namesList;
    }




}
