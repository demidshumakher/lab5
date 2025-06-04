package ru.ifmo.se.commands;

import ru.ifmo.se.collectionManager.CollectionManager;
import ru.ifmo.se.exceptions.WrongAmountOfArguments;
import ru.ifmo.se.exceptions.WrongInputException;
import ru.ifmo.se.models.MusicGenre;
import ru.ifmo.se.utils.IOManagers.IOManager;

import java.io.IOException;

/**
 * Command that counts the number of music bands with a specified genre.
 * This command requires exactly two arguments: the command name and the genre.
 */
public class CountByGenreCommand extends Command {
    /** The collection manager that this command will operate on */
    private final CollectionManager collectionManager;

    /**
     * Constructs a new CountByGenreCommand with the specified collection manager.
     * 
     * @param collectionManager the collection manager to operate on
     */
    public CountByGenreCommand(CollectionManager collectionManager) {
        super("count_by_genre [genre]", "возвращает количество групп по заданному жанру");
        this.collectionManager = collectionManager;
    }

    /**
     * Executes the count_by_genre command, counting the number of music bands with the specified genre.
     * 
     * @param ioManager the IO manager to use for input/output operations
     * @param arguments the arguments to pass to the command (should be the command name and the genre)
     * @return true if the command was executed successfully
     * @throws WrongAmountOfArguments if the number of arguments is not 2
     * @throws IOException if an I/O error occurs
     */
    @Override
    public boolean execute(IOManager ioManager, String[] arguments) throws WrongInputException, WrongAmountOfArguments, IOException {
        if (arguments.length != 2) {
            throw new WrongAmountOfArguments();
        }
        MusicGenre genre;
        try {
            genre = MusicGenre.valueOf(arguments[1].toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new WrongInputException("неизвестный жанр");
        }
        long count = collectionManager.getItems().filter(element -> element.getGenre().equals(genre)).count();
        ioManager.write("Количество групп с жанром " + genre + ": " + count + "\n");
        return true;
    }
}
