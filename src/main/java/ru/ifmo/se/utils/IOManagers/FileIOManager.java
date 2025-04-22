package ru.ifmo.se.utils.IOManagers;

import java.io.*;

public class IOManagerWithoutOutput extends BaseIOManager{
    public IOManagerWithoutOutput(String fileName, Writer output) throws FileNotFoundException {
        super(new BufferedReader(new FileReader(fileName)), output);
    }

    @Override
    public String readLine(String prompt) throws IOException {
        return this.readLine("");
    }

}
