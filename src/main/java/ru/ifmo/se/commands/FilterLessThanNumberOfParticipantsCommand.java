package ru.ifmo.se.commands;

import ru.ifmo.se.collectionManager.CollectionManager;
import ru.ifmo.se.exceptions.WrongAmountOfArguments;
import ru.ifmo.se.exceptions.WrongInputException;
import ru.ifmo.se.utils.IOManagers.IOManager;

import java.io.IOException;

public class FilterLessThanNumberOfParticipantsCommand extends Command {
    private final CollectionManager collectionManager;

    public FilterLessThanNumberOfParticipantsCommand(CollectionManager collectionManager) {
        super("filter less than number of participants", "вывести элементы, значение поля numberOfParticipants которых меньше заданного");
        this.collectionManager = collectionManager;
    }

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
        collectionManager.getItems().filter(element -> element.getNumberOfParticipants() < number).forEach(musicBand -> ioManager.write(musicBand + ", "));
        ioManager.write("}\n");

        return true;
    }

}
