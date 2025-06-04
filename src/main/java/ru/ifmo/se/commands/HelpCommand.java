package ru.ifmo.se.commands;

import ru.ifmo.se.commandManager.CommandManager;
import ru.ifmo.se.utils.IOManagers.IOManager;
import java.io.IOException;

/**
 * Command that displays help information about all available commands.
 * This command does not require any arguments.
 */
public class HelpCommand extends Command {

    CommandManager commandManager;

    /**
     * Constructs a new HelpCommand with the name "help" and appropriate description.
     * @param commandManager the command manager that gets a list of commands with description
     */
    public HelpCommand(CommandManager commandManager) {
        super("help", "выводит справку по доступным командам");
        this.commandManager = commandManager;
    }

    /**
     * Executes the help command, displaying information about all available commands.
     * 
     * @param ioManager the IO manager to use for output
     * @param arguments command arguments (not used for this command)
     * @return true if the command was executed successfully
     * @throws IOException if an I/O error occurs
     */
    @Override
    public boolean execute(IOManager ioManager, String[] arguments) throws IOException {
        for (var key : this.commandManager.getCommands().keySet()) {
            var command = this.commandManager.getCommands().get(key);
            ioManager.writeln(command.getName() + " : " + command.getDescription());
        }
        return true;
    }
}
