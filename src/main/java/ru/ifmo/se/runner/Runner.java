package ru.ifmo.se.runner;

import ru.ifmo.se.collectionManager.CollectionManager;
import ru.ifmo.se.commandManager.CommandManager;
import ru.ifmo.se.commands.Command;
import ru.ifmo.se.exceptions.UnknownCommandException;
import ru.ifmo.se.exceptions.WrongAmountOfArguments;
import ru.ifmo.se.exceptions.WrongInputException;
import ru.ifmo.se.utils.IOManagers.IOManager;

import java.io.IOException;
import java.util.List;

/**
 * The Runner class is responsible for running the application's command loop.
 * It reads commands from the IOManager, executes them using the CommandManager,
 * and handles any exceptions that occur during execution.
 */
public class Runner {
    /** The IO manager used for reading commands and writing output */
    private final IOManager ioManager;

    /** The command manager used for retrieving and executing commands */
    private final CommandManager commandManager;

    /**
     * Constructs a new Runner with the specified IO manager and command manager.
     * 
     * @param ioManager the IO manager to use for input/output
     * @param commandManager the command manager to use for command execution
     */
    public Runner(IOManager ioManager, CommandManager commandManager) {
        this.ioManager = ioManager;
        this.commandManager = commandManager;
    }

    /**
     * Runs the command loop, reading commands from the IO manager and executing them.
     * The loop continues until the IO manager returns null (end of input).
     * 
     * @throws IOException if an I/O error occurs
     */
    public void run() throws IOException {
        ioManager.writeln("IDLE");
        String line;
        while ((line = ioManager.readLine(">")) != null) {
            String[] arguments = line.split(" ");
            if (arguments.length == 0) {
                continue;
            }
            String commandName = arguments[0];
            Command command;
            try {
                command = commandManager.getCommand(commandName);
            } catch (UnknownCommandException e) {
                ioManager.writeln("{Неизвестная команда: " + commandName + " }");
                continue;
            }

            try {
                boolean ok = command.execute(ioManager, arguments);
                if (ok) {
                    ioManager.writeln("{Command executed successfully}");
                } else {
                    ioManager.writeln("{Command execution failed}");
                }
            } catch (WrongAmountOfArguments e) {
                ioManager.writeln("Неверное количество аргументов");
            } catch (WrongInputException e) {
                ioManager.writeln("Неверный ввод " + e.getMessage());
            }
        }
        ioManager.writeln("\n{EOF}");
    }
}
