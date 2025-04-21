package ru.ifmo.se.commands;

import ru.ifmo.se.collectionManager.CollectionManager;
import ru.ifmo.se.exceptions.WrongInputException;
import ru.ifmo.se.utils.IOManagers.IOManager;

public class RemoveAtIndexCommand extends Command {
    private final CollectionManager collectionManager;
    public RemoveAtIndexCommand(CollectionManager collectionManger) {
        super("remove at index", "удаляет элемент с индексов");
        this.collectionManager = collectionManger;
    }

    @Override
    public boolean execute(IOManager ioManager, String[] arguments) throws WrongInputException {
        if (arguments.length != 2) {
            throw new IllegalArgumentException();
        }
        int index;
        try {
            index = Integer.parseInt(arguments[1]);
        } catch (NumberFormatException e) {
            throw new WrongInputException("Число введено не верно");
        }
        this.collectionManager.removeAtIndex(index);
        return true;
    }

}
