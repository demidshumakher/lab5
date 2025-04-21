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

public class Runner {
    private final IOManager ioManager;
    private final CommandManager commandManager;

    public Runner(IOManager ioManager, CommandManager commandManager) {
        this.ioManager = ioManager;
        this.commandManager = commandManager;
    }

    public void run() {
        try {
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
        } catch (IOException e) {
            System.out.println("Ошибка ввода вывода");
            return;
        }
        System.out.println("EOF, ввод закончен");
    }
}
