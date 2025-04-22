package ru.ifmo.se.commands;

import ru.ifmo.se.exceptions.WrongAmountOfArguments;
import ru.ifmo.se.exceptions.WrongInputException;
import ru.ifmo.se.utils.IOManagers.IOManager;

import java.io.IOException;

/**
 * Interface for executable commands in the application.
 * Classes that implement this interface can be executed with an IOManager and arguments.
 */
public interface Executable {
    /**
     * Executes the command with the specified IO manager and arguments.
     * 
     * @param ioManager the IO manager to use for input/output operations
     * @param arguments the arguments to pass to the command
     * @return true if the command was executed successfully, false otherwise
     * @throws IOException if an I/O error occurs
     * @throws WrongAmountOfArguments if the number of arguments is incorrect
     * @throws WrongInputException if the input is invalid
     */
    boolean execute(IOManager ioManager, String[] arguments) throws IOException, WrongAmountOfArguments, WrongInputException;
}
