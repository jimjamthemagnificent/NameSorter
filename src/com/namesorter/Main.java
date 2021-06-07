package com.namesorter;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.namesorter.activity.NameSorter;
import com.namesorter.exception.InvalidInputException;
import com.namesorter.model.Name;
import com.namesorter.module.IOModule;

import java.io.File;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new IOModule());
        NameSorter nameSorter = injector.getInstance(NameSorter.class);

        ValidateArgs(args);

        String filePath = args[0];
        List<Name> nameList = nameSorter.getSortedNamesFromFile(filePath);
        nameList.forEach(System.out::println);

    }

    private static void ValidateArgs(String[] args) {
        File file = new File(args[0]);
        if (args.length != 1 || !file.exists()) {
           throw new InvalidInputException("Please check unsorted name list file path and try again.");
        }
    }
}
