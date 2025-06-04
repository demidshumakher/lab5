package ru.ifmo.se.commands;

import ru.ifmo.se.collectionManager.CollectionManager;
import ru.ifmo.se.exceptions.WrongAmountOfArguments;
import ru.ifmo.se.utils.IOManagers.IOManager;

import java.io.IOException;

/**
 * Command that displays all elements in the collection.
 * This command requires exactly one argument (the command name itself).
 */
public class ShowCommand extends Command {

    /** The collection manager that provides access to the collection */
    private final CollectionManager collectionManager;

    /**
     * Constructs a new ShowCommand with the specified collection manager.
     * 
     * @param collectionManager the collection manager to use
     */
    public ShowCommand(CollectionManager collectionManager) {
        super("show", "выводит все элементы коллекции");
        this.collectionManager = collectionManager;
    }

    /**
     * Executes the show command, displaying all elements in the collection.
     * 
     * @param ioManager the IO manager to use for output
     * @param arguments command arguments (should contain only the command name)
     * @return true if the command was executed successfully
     * @throws WrongAmountOfArguments if the number of arguments is not exactly 1
     * @throws IOException if an I/O error occurs
     */
    @Override
    public boolean execute(IOManager ioManager, String[] arguments) throws WrongAmountOfArguments, IOException {
        if (arguments.length != 1) {
            throw new WrongAmountOfArguments();
        }

        for (var item : this.collectionManager.getItems().toList()) {
            ioManager.write(item + "\n\n");
        }

        return true;
    }
}
