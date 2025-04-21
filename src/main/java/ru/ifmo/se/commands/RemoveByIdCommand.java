package ru.ifmo.se.commands;

import ru.ifmo.se.collectionManager.CollectionManager;
import ru.ifmo.se.exceptions.WrongAmountOfArguments;
import ru.ifmo.se.exceptions.WrongInputException;
import ru.ifmo.se.utils.IOManagers.IOManager;

public class RemoveByIdCommand extends Command {
    private final CollectionManager collectionManager;
    public RemoveByIdCommand(CollectionManager collectionManager) {
        super("remove by id", "удалить элемент из коллекции по id");
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute(IOManager ioManager, String[] arguments) throws WrongInputException, WrongAmountOfArguments {
        if (arguments.length != 2) {
            throw new WrongAmountOfArguments();
        }
        int id;
        try {
            id = Integer.parseInt(arguments[1]);
        } catch (NumberFormatException e) {
            throw new WrongInputException("Число введено не верно");
        }
        this.collectionManager.removeById(id);
        return true;
    }
}
