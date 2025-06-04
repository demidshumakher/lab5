package ru.ifmo.se.commands;

import ru.ifmo.se.collectionManager.CollectionManager;
import ru.ifmo.se.exceptions.WrongAmountOfArguments;
import ru.ifmo.se.exceptions.WrongInputException;
import ru.ifmo.se.models.forms.MusicBandForm;
import ru.ifmo.se.models.MusicBand;
import ru.ifmo.se.utils.IOManagers.IOManager;

import java.io.IOException;

/**
 * Command that adds a new element to the collection.
 * This command creates a new MusicBand object and adds it to the collection.
 */
public class AddCommand extends Command {

    /** The collection manager that this command will operate on */
    private final CollectionManager collectionManager;

    /**
     * Constructs a new AddCommand with the specified collection manager.
     * 
     * @param collectionManager the collection manager to operate on
     */
    public AddCommand(CollectionManager collectionManager) {
        super("add {element}", "добавляет новый элемент в коллекцию");
        this.collectionManager = collectionManager;
    }

    /**
     * Executes the add command, creating a new MusicBand and adding it to the collection.
     * 
     * @param ioManager the IO manager to use for input/output operations
     * @param arguments the arguments to pass to the command (should be just the command name)
     * @return true if the command was executed successfully, false otherwise
     * @throws WrongAmountOfArguments if the number of arguments is not 1
     * @throws IOException if an I/O error occurs
     * @throws WrongInputException if the input is invalid
     */
    @Override
    public boolean execute(IOManager ioManager, String[] arguments) throws WrongAmountOfArguments, IOException, WrongInputException {
        if (arguments.length != 1) {
            throw new WrongAmountOfArguments();
        }

        MusicBand musicBand = new MusicBandForm(ioManager).get();
        return collectionManager.add(musicBand);
    }
}
