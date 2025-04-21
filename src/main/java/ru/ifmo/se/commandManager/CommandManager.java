package ru.ifmo.se.commandManager;

import ru.ifmo.se.commands.Command;
import ru.ifmo.se.exceptions.UnknownCommandException;

import java.util.HashMap;
import java.util.Map;

public class CommandManager {
    private final Map<String, Command> commands = new HashMap<>();

    public CommandManager() {
    }


    public void register(String commandName, Command command) {
        commands.put(commandName, command);
    }

    public Command getCommand(String commandName) throws UnknownCommandException {
        if (!commands.containsKey(commandName)) {
            throw new UnknownCommandException();
        }
        return commands.get(commandName);
    }

    public Map<String, Command> getCommands() {
        return commands;
    }
}
