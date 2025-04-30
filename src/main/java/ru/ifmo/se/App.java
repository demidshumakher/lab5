package ru.ifmo.se;

import ru.ifmo.se.collectionManager.CollectionManager;
import ru.ifmo.se.commandManager.CommandManager;
import ru.ifmo.se.commands.*;
import ru.ifmo.se.exceptions.WrongInputException;
import ru.ifmo.se.models.comporators.MusicBandCreationDateComparator;
import ru.ifmo.se.runner.Runner;
import ru.ifmo.se.utils.IOManagers.BaseIOManager;
import ru.ifmo.se.utils.IOManagers.IOManager;

import java.io.*;
import java.util.Arrays;
import java.util.HashSet;

/**
 * Main application class that serves as the entry point for the program.
 * This class initializes the collection manager, command manager, and registers
 * all available commands. It also handles the main execution loop of the application.
 * The application manages a collection of music bands stored in an XML file.
 */
public class App {
    public static void main(String[] args) {
        String filename = "db.xml";


        if (args.length == 0) {
            System.out.println("Используется файл по умолчания " + filename);
        } else {
            filename = args[0];
            System.out.println("Используется файл " + filename);
        }

        File file = new File(filename);
        InputStreamReader db;
        CollectionManager collectionManager;

        try {
            if (!file.exists()) {
                System.out.println("Файл не найден, создаем новый файл: " + filename);
                file.createNewFile();

                collectionManager = new CollectionManager();

                try (FileWriter writer = new FileWriter(file)) {
                    collectionManager.serialize(writer);
                }
            } else {
                db = new FileReader(filename);
                collectionManager = CollectionManager.deserialize(db);
                db.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage() + "\nЗавершение работы");
            return;
        }


        IOManager ioManager = new BaseIOManager(new InputStreamReader(System.in), new OutputStreamWriter(System.out));
        CommandManager commandManager = new CommandManager();

        commandManager.register("help", new HelpCommand());
        commandManager.register("info", new InfoCommand(collectionManager));
        commandManager.register("show", new ShowCommand(collectionManager));
        commandManager.register("add", new AddCommand(collectionManager));
        commandManager.register("remove_by_id", new RemoveByIdCommand(collectionManager));
        commandManager.register("clear", new ClearCommand(collectionManager));
        commandManager.register("exit", new ExitCommand());
        commandManager.register("remove_at_index", new RemoveAtIndexCommand(collectionManager));
        commandManager.register("remove_last", new RemoveLastCommand(collectionManager));
        commandManager.register("count_by_genre", new CountByGenreCommand(collectionManager));
        commandManager.register("filter_starts_with_name", new FilterStartsWithNameCommand(collectionManager));
        commandManager.register("filter_less_than_number_of_participants", new FilterLessThanNumberOfParticipantsCommand(collectionManager));
        commandManager.register("update_by_id", new UpdateByIdCommand(collectionManager));
        commandManager.register("save", new SaveCommand(collectionManager, filename));
        commandManager.register("add_if_max", new AddIfMaxCommand(collectionManager, new MusicBandCreationDateComparator()));
        commandManager.register("execute_script", new ExecuteScriptCommand(collectionManager, commandManager, new HashSet<>()));

        // todo filter

        Runner runner = new Runner(ioManager, commandManager);

        while (true) {
            try {
                runner.run();
            } catch (IOException e) {
                System.out.println("{Для выхода используйте команду exit}");
            }
        }

    }
}
