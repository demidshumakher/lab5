package ru.ifmo.se.commands;

import ru.ifmo.se.collectionManager.CollectionManager;
import ru.ifmo.se.exceptions.WrongAmountOfArguments;
import ru.ifmo.se.utils.IOManagers.IOManager;


public class ClearCommand extends Command {
    private final CollectionManager collectionManager;


    public ClearCommand(CollectionManager collectionManager) {
        super("clear", "очищает коллекцию");
        this.collectionManager = collectionManager;
    }


    @Override
    public boolean execute(IOManager ioManager, String[] arguments) throws WrongAmountOfArguments {
        if (arguments.length != 1) {
            throw new WrongAmountOfArguments();
        }
        collectionManager.clear();
        return true;
    }
}
