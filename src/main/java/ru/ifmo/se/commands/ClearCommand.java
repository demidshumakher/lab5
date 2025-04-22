package ru.ifmo.se.commands;

import ru.ifmo.se.collectionManager.CollectionManager;
import ru.ifmo.se.exceptions.WrongAmountOfArguments;
import ru.ifmo.se.utils.IOManagers.IOManager;

/**
 * Command that clears all elements from the collection.
 * This command removes all elements from the collection managed by the collection manager.
 */
public class ClearCommand extends Command {
    /** The collection manager that this command will operate on */
    private final CollectionManager collectionManager;

    /**
     * Constructs a new ClearCommand with the specified collection manager.
     * 
     * @param collectionManager the collection manager to operate on
     */
    public ClearCommand(CollectionManager collectionManager) {
        super("clear", "очищает коллекцию");
        this.collectionManager = collectionManager;
    }

    /**
     * Executes the clear command, removing all elements from the collection.
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
        collectionManager.clear();
        return true;
    }
}
