package ru.ifmo.se.commands;

import ru.ifmo.se.collectionManager.CollectionManager;
import ru.ifmo.se.exceptions.WrongAmountOfArguments;
import ru.ifmo.se.utils.IOManagers.IOManager;

/**
 * Command that removes the last element from the collection.
 * This command requires exactly one argument: the command name.
 */
public class RemoveLastCommand extends Command {
    /** The collection manager that this command will operate on */
    private final CollectionManager collectionManager;

    /**
     * Constructs a new RemoveLastCommand with the specified collection manager.
     * 
     * @param collectionManager the collection manager to operate on
     */
    public RemoveLastCommand(CollectionManager collectionManager) {
        super("remove last element", "удаляет последний элемент");
        this.collectionManager = collectionManager;
    }

    /**
     * Executes the remove_last command, removing the last element from the collection.
     * 
     * @param ioManager the IO manager to use for input/output operations
     * @param arguments the arguments to pass to the command (should be just the command name)
     * @return true if the command was executed successfully
     * @throws WrongAmountOfArguments if the number of arguments is not 1
     */
    @Override
    public boolean execute(IOManager ioManager, String[] arguments) throws WrongAmountOfArguments {
        if (arguments.length != 1) {
            throw new WrongAmountOfArguments();
        }
        this.collectionManager.removeLast();
        return true;
    }
}
