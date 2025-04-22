package ru.ifmo.se.commands;

import ru.ifmo.se.exceptions.WrongAmountOfArguments;
import ru.ifmo.se.exceptions.WrongInputException;
import ru.ifmo.se.utils.IOManagers.IOManager;

import java.io.IOException;
import java.util.Objects;

/**
 * Abstract base class for all commands in the application.
 * This class provides common functionality for commands, such as name and description,
 * and defines the execute method that must be implemented by all concrete command classes.
 */
public abstract class Command implements Executable {
    /** The name of the command */
    String name;
    /** The description of the command */
    String description;

    /**
     * Constructs a new Command with the specified name and description.
     * 
     * @param name the name of the command
     * @param description the description of the command
     */
    public Command(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * Returns the name of the command.
     * 
     * @return the name of the command
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the description of the command.
     * 
     * @return the description of the command
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Compares this command to the specified object for equality.
     * Two commands are considered equal if they have the same name and description.
     * 
     * @param o the object to compare with
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Command command = (Command) o;
        return Objects.equals(name, command.name) && Objects.equals(description, command.description);
    }

    /**
     * Returns a hash code value for this command.
     * 
     * @return a hash code value for this command
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, description);
    }

    /**
     * Returns a string representation of this command.
     * 
     * @return a string representation of this command
     */
    @Override
    public String toString() {
        return "Command{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

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
    public abstract boolean execute(IOManager ioManager, String[] arguments) throws IOException, WrongAmountOfArguments, WrongInputException;
}
