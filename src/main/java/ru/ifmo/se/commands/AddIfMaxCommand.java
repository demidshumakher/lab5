package ru.ifmo.se.commands;

import ru.ifmo.se.collectionManager.CollectionManager;
import ru.ifmo.se.exceptions.WrongAmountOfArguments;
import ru.ifmo.se.exceptions.WrongInputException;
import ru.ifmo.se.models.forms.MusicBandForm;
import ru.ifmo.se.models.MusicBand;
import ru.ifmo.se.utils.IOManagers.IOManager;

import java.io.IOException;
import java.util.Comparator;

public class AddIfMaxCommand extends Command {

    private final CollectionManager collectionManager;
    private final Comparator<MusicBand> comparator;

    public AddIfMaxCommand(CollectionManager collectionManager, Comparator<MusicBand> comparator) {
        super("add if max", "Добавляет элемент, если он максимальный");
        this.collectionManager = collectionManager;
        this.comparator = comparator;
    }

    @Override
    public boolean execute(IOManager ioManager, String[] arguments) throws IOException, WrongAmountOfArguments, WrongInputException {
        if (arguments.length != 1) {
            throw new WrongAmountOfArguments();
        }
        MusicBand musicBand = new MusicBandForm(ioManager).get();

        return null == collectionManager.getItems()
                .filter(element -> comparator.compare(element, musicBand) < 0)
                .findFirst().orElseGet(() -> {
                    collectionManager.add(musicBand);
                    return null;
                });
    }


}
