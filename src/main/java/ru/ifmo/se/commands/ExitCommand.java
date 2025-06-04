package ru.ifmo.se.commands;

import ru.ifmo.se.exceptions.WrongAmountOfArguments;
import ru.ifmo.se.utils.IOManagers.IOManager;

/**
 * Command that terminates the application.
 * This command exits the program immediately with status code 0.
 */
public class ExitCommand extends Command {
    /**
     * Constructs a new ExitCommand.
     */
    public ExitCommand() {
        super("exit", "завершает работу");
    }

    /**
     * Executes the exit command, terminating the application.
     * 
     * @param ioManager the IO manager to use for input/output operations
     * @param arguments the arguments to pass to the command (should be just the command name)
     * @return true if the command was executed successfully (though this will never be returned as the program exits)
     * @throws WrongAmountOfArguments if the number of arguments is not 1
     */
    @Override
    public boolean execute(IOManager ioManager, String[] arguments) throws WrongAmountOfArguments {
        if (arguments.length != 1) {
            throw new WrongAmountOfArguments();
        }
        System.exit(0);
        return true;
    }



}
