package ru.ifmo.se.commandManager;

import ru.ifmo.se.commands.Command;
import ru.ifmo.se.exceptions.UnknownCommandException;

import java.util.HashMap;
import java.util.Map;

/**
 * The CommandManager class is responsible for managing commands in the application.
 * It provides methods for registering commands, retrieving commands by name,
 * and getting all registered commands.
 */
public class CommandManager {
    /** Map that stores commands with their names as keys */
    private final Map<String, Command> commands = new HashMap<>();

    /**
     * Constructs a new empty CommandManager.
     */
    public CommandManager() {
    }

    /**
     * Registers a command with the specified name.
     * 
     * @param commandName the name of the command
     * @param command the command to register
     */
    public void register(String commandName, Command command) {
        commands.put(commandName, command);
    }

    /**
     * Retrieves a command by its name.
     * 
     * @param commandName the name of the command to retrieve
     * @return the command with the specified name
     * @throws UnknownCommandException if no command with the specified name is registered
     */
    public Command getCommand(String commandName) throws UnknownCommandException {
        if (!commands.containsKey(commandName)) {
            throw new UnknownCommandException();
        }
        return commands.get(commandName);
    }

    /**
     * Returns a map of all registered commands.
     * 
     * @return a map containing all registered commands with their names as keys
     */
    public Map<String, Command> getCommands() {
        return commands;
    }
}
