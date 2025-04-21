package ru.ifmo.se.commands;

import ru.ifmo.se.collectionManager.CollectionManager;
import ru.ifmo.se.exceptions.WrongAmountOfArguments;
import ru.ifmo.se.utils.IOManagers.FileIOManager;
import ru.ifmo.se.utils.IOManagers.IOManager;

import java.io.FileNotFoundException;
import java.io.Reader;

public class ExecuteScriptCommand extends Command {

    private final CollectionManager collectionManager;

    public ExecuteScriptCommand(CollectionManager collectionManager) {
        super("Execute script", "исполняет скрипт из файла");
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute(IOManager ioManager, String[] arguments) {
//        if (arguments.length != 2) {
//            throw new WrongAmountOfArguments();
//        }
//        String fileName = arguments[1];
//
//        IOManager newIOMnager;
//        try {
//            ioManager = new FileIOManager(fileName, );
//        } catch (FileNotFoundException e) {
//
//        }


        return false;
    }

}
