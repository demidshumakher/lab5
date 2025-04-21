package ru.ifmo.se.utils.IOManagers;

import java.io.*;

public class BaseIOManager implements IOManager {
    private final BufferedReader reader;
    private final Writer writer;

    public BaseIOManager(Reader reader, Writer writer) {
        this.reader = new BufferedReader(reader);
        this.writer = writer;
    }

    public void write(String data) throws IOException {
        this.writer.write(data);
        this.writer.flush();
    }

    public void writeln(String data) throws IOException {
        this.write(data + "\n");
    }

    public String readLine(String prompt) throws IOException {
        this.write(prompt + " ");
        return reader.readLine();
    }

}
