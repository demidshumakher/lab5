package ru.ifmo.se.commands;

import ru.ifmo.se.exceptions.WrongAmountOfArguments;
import ru.ifmo.se.utils.IOManagers.IOManager;

public class ExitCommand extends Command {
    public ExitCommand() {
        super("exit", "Завершение работы");
    }

    @Override
    public boolean execute(IOManager ioManager, String[] arguments) throws WrongAmountOfArguments {
        if (arguments.length != 1) {
            throw new WrongAmountOfArguments();
        }
        System.exit(0);
        return true;
    }



}
