package ru.ifmo.se.utils.IOManagers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Writer;

public class FileIOManager extends BaseIOManager{
    public FileIOManager(String fileName, Writer output) throws FileNotFoundException {
        super(new BufferedReader(new FileReader(fileName)), output);
    }
}
