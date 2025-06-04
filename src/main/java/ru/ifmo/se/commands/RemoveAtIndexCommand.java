package ru.ifmo.se.commands;

import ru.ifmo.se.collectionManager.CollectionManager;
import ru.ifmo.se.exceptions.WrongInputException;
import ru.ifmo.se.utils.IOManagers.IOManager;

/**
 * Command that removes an element at the specified index from the collection.
 * This command requires exactly two arguments: the command name and the index.
 */
public class RemoveAtIndexCommand extends Command {
    /** The collection manager that this command will operate on */
    private final CollectionManager collectionManager;

    /**
     * Constructs a new RemoveAtIndexCommand with the specified collection manager.
     * 
     * @param collectionManger the collection manager to operate on
     */
    public RemoveAtIndexCommand(CollectionManager collectionManger) {
        super("remove_at [index]", "удаляет элемент по заданному индексу");
        this.collectionManager = collectionManger;
    }

    /**
     * Executes the remove_at_index command, removing the element at the specified index.
     * 
     * @param ioManager the IO manager to use for input/output operations
     * @param arguments the arguments to pass to the command (should be the command name and the index)
     * @return true if the command was executed successfully
     * @throws WrongInputException if the provided index is not a valid integer
     * @throws IllegalArgumentException if the number of arguments is not 2
     */
    @Override
    public boolean execute(IOManager ioManager, String[] arguments) throws WrongInputException {
        if (arguments.length != 2) {
            throw new IllegalArgumentException();
        }
        int index;
        try {
            index = Integer.parseInt(arguments[1]);
        } catch (NumberFormatException e) {
            throw new WrongInputException("Число введено не верно");
        }

        this.collectionManager.removeAtIndex(index);

        return true;
    }

}
