package ru.ifmo.se.commands;

import ru.ifmo.se.collectionManager.CollectionManager;
import ru.ifmo.se.utils.IOManagers.IOManager;

import java.io.IOException;

public class FilterStartsWithNameCommand extends Command {
    private final CollectionManager collectionManager;

    public FilterStartsWithNameCommand(CollectionManager collectionManager) {
        super("Filter starts with name", "вывести элементы, значение поля name которых начинается с заданной подстроки");
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute(IOManager ioManager, String[] arguments) throws IOException {
        if (arguments.length != 2) {
            throw new IllegalArgumentException();
        }
        String name = arguments[1];
        ioManager.write("Result{ ");

        for (var item : this.collectionManager.getItems()
                .filter(element -> element.getName().startsWith(name)).toList()) {
            ioManager.write(item + ", ");
        }
        ioManager.writeln("}");
        return true;
    }


}
