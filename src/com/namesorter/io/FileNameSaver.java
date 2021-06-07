package com.namesorter.io;

import com.namesorter.exception.SavingException;
import com.namesorter.model.Name;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.io.FileWriter;

public class FileNameSaver {

    private final String saveFilePath;

    public FileNameSaver(String saveFilePath) {
        this.saveFilePath = saveFilePath;
    }

    public void saveNamesToFile(List<Name> nameList){
        File sortedFile = new File(saveFilePath);
        try (FileWriter writer = new FileWriter(sortedFile)){
            nameList.forEach(name -> writeLine(name, writer));
        }
        catch(IOException e){
            throw new SavingException(e.getMessage(), e);
        }
    }

    private void writeLine(Name name, FileWriter writer){
        try {
            writer.write(name + System.getProperty("line.separator"));
        }
        catch (IOException e){
            throw new SavingException("I/O Exception occurred while writing name: " + name, e);
        }
    }
}
