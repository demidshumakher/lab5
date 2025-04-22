package ru.ifmo.se.utils.IOManagers;

import java.io.*;

public class FileIOManager extends BaseIOManager{
    public FileIOManager(String fileName, Writer output) throws FileNotFoundException {
        super(new BufferedReader(new FileReader(fileName)), output);
    }

    @Override
    public String readLine(String prompt) throws IOException {
        return super.readLine("");
    }

}
