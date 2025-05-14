package ru.ifmo.se.commands;

import ru.ifmo.se.collectionManager.CollectionManager;
import ru.ifmo.se.exceptions.WrongAmountOfArguments;
import ru.ifmo.se.exceptions.WrongInputException;
import ru.ifmo.se.utils.IOManagers.IOManager;

import java.io.IOException;

/**
 * Command that filters elements by the number of participants.
 * This command displays all elements whose numberOfParticipants field is less than the specified value.
 * This command requires exactly two arguments: the command name and the number to compare with.
 */
public class FilterLessThanNumberOfParticipantsCommand extends Command {
    /** The collection manager that this command will operate on */
    private final CollectionManager collectionManager;

    /**
     * Constructs a new FilterLessThanNumberOfParticipantsCommand with the specified collection manager.
     * 
     * @param collectionManager the collection manager to operate on
     */
    public FilterLessThanNumberOfParticipantsCommand(CollectionManager collectionManager) {
        super("filter less than number of participants", "вывести элементы, значение поля numberOfParticipants которых меньше заданного");
        this.collectionManager = collectionManager;
    }

    /**
     * Executes the filter_less_than_number_of_participants command, displaying all elements
     * whose numberOfParticipants field is less than the specified value.
     * 
     * @param ioManager the IO manager to use for input/output operations
     * @param arguments the arguments to pass to the command (should be the command name and the number to compare with)
     * @return true if the command was executed successfully
     * @throws WrongInputException if the provided number is not a valid integer
     * @throws WrongAmountOfArguments if the number of arguments is not 2
     * @throws IOException if an I/O error occurs
     */
    @Override
    public boolean execute(IOManager ioManager, String[] arguments) throws WrongInputException, WrongAmountOfArguments, IOException {
        if (arguments.length != 2) {
            throw new WrongAmountOfArguments();
        }
        Integer number;
        try {
            number = Integer.parseInt(arguments[1]);
        } catch (NumberFormatException e) {
            throw new WrongInputException("Число введено не верно");
        }
        ioManager.write("Result{ ");
        for (var item : collectionManager.getItems()
                .filter(element -> element.getNumberOfParticipants() < number)
                .toList()) {
            ioManager.write(item + ",\n");
        }
        ioManager.writeln("}");

        return true;
    }

}
