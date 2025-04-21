package ru.ifmo.se.commands;

import ru.ifmo.se.collectionManager.CollectionManager;
import ru.ifmo.se.exceptions.WrongAmountOfArguments;
import ru.ifmo.se.models.MusicGenre;
import ru.ifmo.se.utils.IOManagers.IOManager;

import java.io.IOException;

public class CountByGenreCommand extends Command {
    private final CollectionManager collectionManager;

    public CountByGenreCommand(CollectionManager collectionManager) {
        super("Count by genre", "возвращает количество групп по заданному жанру");
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute(IOManager ioManager, String[] arguments) throws WrongAmountOfArguments, IOException {
        if (arguments.length != 2) {
            throw new WrongAmountOfArguments();
        }
        MusicGenre genre = MusicGenre.valueOf(arguments[1].toUpperCase());
        long count = collectionManager.getItems().filter(element -> element.getGenre().equals(genre)).count();
        ioManager.write("Количество групп с жанром " + genre + ": " + count + "\n");
        return true;
    }

}
