package ru.ifmo.se.commands;

import ru.ifmo.se.collectionManager.CollectionManager;
import ru.ifmo.se.exceptions.WrongAmountOfArguments;
import ru.ifmo.se.utils.IOManagers.IOManager;

import java.io.IOException;

/**
 * Command that filters elements by the name field.
 * This command displays all elements whose name field starts with the specified substring.
 * This command requires exactly two arguments: the command name and the substring to match.
 */
public class FilterStartsWithNameCommand extends Command {
    /** The collection manager that this command will operate on */
    private final CollectionManager collectionManager;

    /**
     * Constructs a new FilterStartsWithNameCommand with the specified collection manager.
     * 
     * @param collectionManager the collection manager to operate on
     */
    public FilterStartsWithNameCommand(CollectionManager collectionManager) {
        super("filter_starts_with_name [name]", "вывести элементы, значение поля name которых начинается с заданной подстроки");
        this.collectionManager = collectionManager;
    }

    /**
     * Executes the filter_starts_with_name command, displaying all elements
     * whose name field starts with the specified substring.
     * 
     * @param ioManager the IO manager to use for input/output operations
     * @param arguments the arguments to pass to the command (should be the command name and the substring to match)
     * @return true if the command was executed successfully
     * @throws IOException if an I/O error occurs
     * @throws WrongAmountOfArguments if the number of arguments is not 2
     */
    @Override
    public boolean execute(IOManager ioManager, String[] arguments) throws IOException, WrongAmountOfArguments {
        if (arguments.length != 2) {
            throw new WrongAmountOfArguments();
        }
        String name = arguments[1];
        System.out.println("name: " + name);

        for (var item : this.collectionManager.getItems()
                .filter(element -> element.getName().startsWith(name)).toList()) {
            ioManager.write(item + "\n\n");
        }
        return true;
    }


}
