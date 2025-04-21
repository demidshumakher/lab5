package ru.ifmo.se.utils.IOManagers;

import java.io.IOException;

public interface IOManager {
    void write(String data) throws IOException;
    String readLine(String prompt) throws IOException;
    void writeln(String data) throws IOException;
}
