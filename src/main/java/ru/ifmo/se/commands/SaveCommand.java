package ru.ifmo.se.commands;

import ru.ifmo.se.collectionManager.CollectionManager;
import ru.ifmo.se.exceptions.WrongAmountOfArguments;
import ru.ifmo.se.utils.IOManagers.IOManager;


import java.io.FileWriter;
import java.io.IOException;

public class SaveCommand extends Command {

    private final CollectionManager collectionManager;
    private final String fileName;

    public SaveCommand(CollectionManager collectionManager, String fileName) {
        super("save", "сохраняет коллекцию");
        this.collectionManager = collectionManager;
        this.fileName = fileName;
    }

    @Override
    public boolean execute(IOManager ioManager, String[] arguments) throws WrongAmountOfArguments, IOException {
        if (arguments.length != 1) {
            throw new WrongAmountOfArguments();
        }


        FileWriter fw = new FileWriter(this.fileName);

        this.collectionManager.serialize(fw);
        return true;


    }
}
