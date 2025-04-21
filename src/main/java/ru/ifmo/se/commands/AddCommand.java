package ru.ifmo.se.commands;

import ru.ifmo.se.collectionManager.CollectionManager;
import ru.ifmo.se.exceptions.WrongAmountOfArguments;
import ru.ifmo.se.exceptions.WrongInputException;
import ru.ifmo.se.models.forms.MusicBandForm;
import ru.ifmo.se.models.MusicBand;
import ru.ifmo.se.utils.IOManagers.IOManager;

import java.io.IOException;

public class AddCommand extends Command {

    private final CollectionManager collectionManager;

    public AddCommand(CollectionManager collectionManager) {
        super("add", "добавить новый элемент в коллекцию");
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute(IOManager ioManager, String[] arguments) throws WrongAmountOfArguments, IOException, WrongInputException {
        if (arguments.length != 1) {
            throw new WrongAmountOfArguments();
        }

        MusicBand musicBand = new MusicBandForm(ioManager).get();
        return collectionManager.add(musicBand);
    }
}
