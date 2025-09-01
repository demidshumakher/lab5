package ru.ifmo.se.utils.IOManagers;

import java.io.*;

/**
 * File implementation of the IOManager interface.
 * This class uses a BufferedReader for input and a Writer for output.
 */
public class FileIOManager extends BaseIOManager{
    public FileIOManager(String fileName, Writer output) throws FileNotFoundException {
        super(new BufferedReader(new FileReader(fileName)), output);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String readLine(String prompt) throws IOException {
        return super.readLine("");
    }

}
