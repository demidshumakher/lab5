package ru.ifmo.se.commands;

import ru.ifmo.se.collectionManager.CollectionManager;
import ru.ifmo.se.exceptions.WrongAmountOfArguments;
import ru.ifmo.se.exceptions.WrongInputException;
import ru.ifmo.se.utils.IOManagers.IOManager;

/**
 * Command that removes an element with the specified ID from the collection.
 * This command requires exactly two arguments: the command name and the ID.
 */
public class RemoveByIdCommand extends Command {
    /** The collection manager that this command will operate on */
    private final CollectionManager collectionManager;

    /**
     * Constructs a new RemoveByIdCommand with the specified collection manager.
     * 
     * @param collectionManager the collection manager to operate on
     */
    public RemoveByIdCommand(CollectionManager collectionManager) {
        super("remove by id", "удалить элемент из коллекции по id");
        this.collectionManager = collectionManager;
    }

    /**
     * Executes the remove_by_id command, removing the element with the specified ID.
     * 
     * @param ioManager the IO manager to use for input/output operations
     * @param arguments the arguments to pass to the command (should be the command name and the ID)
     * @return true if the command was executed successfully
     * @throws WrongInputException if the provided ID is not a valid integer
     * @throws WrongAmountOfArguments if the number of arguments is not 2
     */
    @Override
    public boolean execute(IOManager ioManager, String[] arguments) throws WrongInputException, WrongAmountOfArguments {
        if (arguments.length != 2) {
            throw new WrongAmountOfArguments();
        }
        int id;
        try {
            id = Integer.parseInt(arguments[1]);
        } catch (NumberFormatException e) {
            throw new WrongInputException("Число введено не верно");
        }
        this.collectionManager.removeById(id);
        return true;
    }
}
