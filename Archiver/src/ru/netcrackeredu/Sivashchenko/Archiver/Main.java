package ru.netcrackeredu.Sivashchenko.Archiver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main{
    public static void main(String[] args) throws IOException{
            ArchiveManager man = new ArchiveManager(args);
            man.archive();
    }
}
