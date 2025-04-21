package ru.ifmo.se.commands;

import ru.ifmo.se.exceptions.WrongAmountOfArguments;
import ru.ifmo.se.exceptions.WrongInputException;
import ru.ifmo.se.utils.IOManagers.IOManager;

import java.io.IOException;

public interface Executable {
    boolean execute(IOManager ioManager, String[] arguments) throws IOException, WrongAmountOfArguments, WrongInputException;
}
