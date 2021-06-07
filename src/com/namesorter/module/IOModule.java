package com.namesorter.module;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.namesorter.io.FileNameSaver;

public class IOModule extends AbstractModule {

    @Provides
    @Singleton
    public FileNameSaver getFileNameSaver() {
        return new FileNameSaver("./sorted-names-list.txt");
    }
}
