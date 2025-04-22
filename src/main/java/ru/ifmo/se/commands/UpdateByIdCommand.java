package ru.ifmo.se.commands;

import ru.ifmo.se.collectionManager.CollectionManager;
import ru.ifmo.se.exceptions.WrongAmountOfArguments;
import ru.ifmo.se.exceptions.WrongInputException;
import ru.ifmo.se.models.forms.MusicBandForm;
import ru.ifmo.se.models.MusicBand;
import ru.ifmo.se.utils.IOManagers.IOManager;

import java.io.IOException;

/**
 * Command that updates an element with the specified ID in the collection.
 * This command requires exactly two arguments: the command name and the ID.
 * After providing the ID, the user will be prompted to enter the new element data.
 */
public class UpdateByIdCommand extends Command {

    /** The collection manager that this command will operate on */
    private final CollectionManager collectionManager;

    /**
     * Constructs a new UpdateByIdCommand with the specified collection manager.
     * 
     * @param collectionManager the collection manager to operate on
     */
    public UpdateByIdCommand(CollectionManager collectionManager) {
        super("update by id", "Обновляет элемент по id");
        this.collectionManager = collectionManager;
    }

    /**
     * Executes the update_by_id command, updating the element with the specified ID.
     * 
     * @param ioManager the IO manager to use for input/output operations
     * @param arguments the arguments to pass to the command (should be the command name and the ID)
     * @return true if the element was successfully updated, false otherwise
     * @throws WrongAmountOfArguments if the number of arguments is not 2
     * @throws WrongInputException if the provided ID is not a valid integer
     * @throws IOException if an I/O error occurs
     */
    @Override
    public boolean execute(IOManager ioManager, String[] arguments) throws WrongAmountOfArguments, WrongInputException, IOException {
        if (arguments.length != 2) {
            throw new WrongAmountOfArguments();
        }
        int id;
        try {
            id = Integer.parseInt(arguments[1]);
        } catch (NumberFormatException e) {
            throw new WrongInputException("ID должен быть числом");
        }
        MusicBand musicBand = new MusicBandForm(ioManager).get();
        return this.collectionManager.updateById(id, musicBand);
    }
}
