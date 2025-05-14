package ru.ifmo.se.commands;

import ru.ifmo.se.collectionManager.CollectionManager;
import ru.ifmo.se.commandManager.CommandManager;
import ru.ifmo.se.exceptions.WrongAmountOfArguments;
import ru.ifmo.se.exceptions.WrongInputException;
import ru.ifmo.se.runner.Runner;
import ru.ifmo.se.utils.IOManagers.IOManager;
import ru.ifmo.se.utils.IOManagers.FileIOManager;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Command that executes a script from a file.
 * This command requires exactly two arguments: the command name and the filename.
 * The script file should contain commands in the same format as they would be entered interactively.
 * Recursive script execution is prevented by tracking the history of executed scripts.
 */
public class ExecuteScriptCommand extends Command {

    /** The collection manager that provides access to the collection */
    private final CollectionManager collectionManager;

    /** The command manager that provides access to all registered commands */
    private final CommandManager commandManager;

    /** Set of absolute paths of scripts that are currently being executed, used to prevent recursive calls */
    private final Set<String> history;

    /**
     * Constructs a new ExecuteScriptCommand with the specified collection manager, command manager, and history set.
     * 
     * @param collectionManager the collection manager to use
     * @param commandManager the command manager to use
     * @param history the set to track script execution history
     */
    public ExecuteScriptCommand(CollectionManager collectionManager, CommandManager commandManager, Set<String> history) {
        super("Execute script", "исполняет скрипт из файла");
        this.collectionManager = collectionManager;
        this.commandManager = commandManager;
        this.history = history;
    }

    /**
     * Executes the execute_script command, running commands from the specified file.
     * 
     * @param ioManager the IO manager to use for output
     * @param arguments command arguments (should contain the command name and the filename)
     * @return true if the script was executed successfully
     * @throws WrongAmountOfArguments if the number of arguments is not exactly 2
     * @throws WrongInputException if the file is not found or if a recursive call is detected
     * @throws IOException if an I/O error occurs
     */
    @Override
    public boolean execute(IOManager ioManager, String[] arguments) throws WrongAmountOfArguments, WrongInputException, IOException {
        if (arguments.length != 2) {
            throw new WrongAmountOfArguments();
        }
        String filename = arguments[1];


        IOManager fileIoManager;
        try {
            fileIoManager = new FileIOManager(filename, new OutputStreamWriter(System.out));
        } catch (FileNotFoundException e) {
            throw new WrongInputException("Файл не найден");
        }

        String path = new File(filename).getAbsolutePath();
        if (this.history.contains(path)) {
            throw new WrongInputException("Рекурсивные вызовы запрещены");
        }

        this.history.add(path);


        Runner runner = new Runner(fileIoManager, this.commandManager);

        try {
            runner.run();
        } catch (EOFException ignore) {
        } catch (IOException e) {
            ioManager.writeln("Ошибка ввода вывода с файла " + e.getMessage());
        }

        this.history.remove(path);

        return true;
    }

}
