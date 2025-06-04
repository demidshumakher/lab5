package ru.ifmo.se.commands;

import ru.ifmo.se.collectionManager.CollectionManager;
import ru.ifmo.se.exceptions.WrongAmountOfArguments;
import ru.ifmo.se.exceptions.WrongInputException;
import ru.ifmo.se.models.comporators.MusicBandCreationDateComparator;
import ru.ifmo.se.models.forms.MusicBandForm;
import ru.ifmo.se.models.MusicBand;
import ru.ifmo.se.utils.IOManagers.IOManager;

import java.io.IOException;
import java.util.Comparator;

/**
 * Command that adds a new element to the collection only if it's the maximum element.
 * The maximum element is determined using the specified comparator.
 * This command requires exactly one argument (the command name itself).
 */
public class AddIfMaxCommand extends Command {

    /** The collection manager that provides access to the collection */
    private final CollectionManager collectionManager;

    /** The comparator used to determine if the new element is the maximum */
    private final Comparator<MusicBand> comparator;

    /**
     * Constructs a new AddIfMaxCommand with the specified collection manager and comparator.
     * 
     * @param collectionManager the collection manager to use
     * @param comparator the comparator to use for determining the maximum element
     */
    public AddIfMaxCommand(CollectionManager collectionManager, Comparator<MusicBand> comparator) {
        super("add_if_max {element}", "Добавляет элемент, если он максимальный");
        this.collectionManager = collectionManager;
        this.comparator = comparator;
    }

    /**
     * Executes the add_if_max command, adding a new element to the collection
     * only if it's the maximum element according to the specified comparator.
     * 
     * @param ioManager the IO manager to use for input/output
     * @param arguments command arguments (should contain only the command name)
     * @return true if the element was added, false otherwise
     * @throws IOException if an I/O error occurs
     * @throws WrongAmountOfArguments if the number of arguments is not exactly 1
     * @throws WrongInputException if the input for the new element is invalid
     */
    @Override
    public boolean execute(IOManager ioManager, String[] arguments) throws IOException, WrongAmountOfArguments, WrongInputException {
        if (arguments.length != 1) {
            throw new WrongAmountOfArguments();
        }
        MusicBand musicBand = new MusicBandForm(ioManager).get();

        var t = collectionManager.getItems()
                .filter(element -> comparator.compare(element, musicBand) > 0)
                .findFirst().orElseGet(() -> {
                    collectionManager.add(musicBand);
                    return null;
                });
        if (t != null) {
            ioManager.writeln("Элемент не является максимальным");
        }
        return true;
    }


}
