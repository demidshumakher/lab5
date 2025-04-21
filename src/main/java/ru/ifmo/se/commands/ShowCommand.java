package ru.ifmo.se.commands;

import ru.ifmo.se.collectionManager.CollectionManager;
import ru.ifmo.se.exceptions.WrongAmountOfArguments;
import ru.ifmo.se.utils.IOManagers.IOManager;

import java.io.IOException;

public class ShowCommand extends Command {

    private final CollectionManager collectionManager;

    public ShowCommand(CollectionManager collectionManager) {
        super("show", "Выводит всю коллекцию");
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute(IOManager ioManager, String[] arguments) throws WrongAmountOfArguments, IOException {
        if (arguments.length != 1) {
            throw new WrongAmountOfArguments();
        }

        ioManager.write("Collection{ ");

        for (var item : this.collectionManager.getItems().toList()) {
            ioManager.write(item + ", ");
        }

        ioManager.writeln("}");
        return true;
    }
}
