package ru.ifmo.se.utils.IOManagers;

import java.io.IOException;

/**
 * Interface for input/output operations in the application.
 * Implementations of this interface handle reading input from a source
 * and writing output to a destination.
 */
public interface IOManager {
    /**
     * Writes the specified data to the output destination.
     * 
     * @param data the data to write
     * @throws IOException if an I/O error occurs
     */
    void write(String data) throws IOException;

    /**
     * Reads a line of text from the input source with the specified prompt.
     * 
     * @param prompt the prompt to display before reading
     * @return the line read from the input source, or null if the end of the input has been reached
     * @throws IOException if an I/O error occurs
     */
    String readLine(String prompt) throws IOException;

    /**
     * Writes the specified data to the output destination, followed by a line separator.
     * 
     * @param data the data to write
     * @throws IOException if an I/O error occurs
     */
    void writeln(String data) throws IOException;
}
