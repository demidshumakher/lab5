package ru.ifmo.se.utils.IOManagers;

import java.io.*;

/**
 * Basic implementation of the IOManager interface.
 * This class uses a BufferedReader for input and a Writer for output.
 */
public class BaseIOManager implements IOManager {
    /** The reader used for input operations */
    private final BufferedReader reader;

    /** The writer used for output operations */
    private final Writer writer;

    /**
     * Constructs a new BaseIOManager with the specified reader and writer.
     * 
     * @param reader the reader to use for input
     * @param writer the writer to use for output
     */
    public BaseIOManager(Reader reader, Writer writer) {
        this.reader = new BufferedReader(reader);
        this.writer = writer;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(String data) throws IOException {
        this.writer.write(data);
        this.writer.flush();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void writeln(String data) throws IOException {
        this.write(data + "\n");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String readLine(String prompt) throws IOException {
        this.write(prompt + " ");
        return reader.readLine();
    }

}
