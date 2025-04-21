package ru.ifmo.se.commands;

import ru.ifmo.se.collectionManager.CollectionManager;
import ru.ifmo.se.exceptions.WrongAmountOfArguments;
import ru.ifmo.se.exceptions.WrongInputException;
import ru.ifmo.se.models.forms.MusicBandForm;
import ru.ifmo.se.models.MusicBand;
import ru.ifmo.se.utils.IOManagers.IOManager;

import java.io.IOException;

public class UpdateByIdCommand extends Command {

    private final CollectionManager collectionManager;
    public UpdateByIdCommand(CollectionManager collectionManager) {
        super("update by id", "Обновляет элемент по id");
        this.collectionManager = collectionManager;
    }


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
