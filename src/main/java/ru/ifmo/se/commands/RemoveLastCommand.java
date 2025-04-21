package ru.ifmo.se.commands;

import ru.ifmo.se.collectionManager.CollectionManager;
import ru.ifmo.se.exceptions.WrongAmountOfArguments;
import ru.ifmo.se.utils.IOManagers.IOManager;

public class RemoveLastCommand extends Command {
    private final CollectionManager collectionManager;
    public RemoveLastCommand(CollectionManager collectionManager) {
        super("remove last element", "удаляет последний элемент");
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute(IOManager ioManager, String[] arguments) throws WrongAmountOfArguments {
        if (arguments.length != 1) {
            throw new WrongAmountOfArguments();
        }
        this.collectionManager.removeLast();
        return true;
    }
}
