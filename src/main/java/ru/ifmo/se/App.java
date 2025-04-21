package ru.ifmo.se;

import ru.ifmo.se.collectionManager.CollectionManager;
import ru.ifmo.se.commandManager.CommandManager;
import ru.ifmo.se.commands.*;
import ru.ifmo.se.models.comporators.MusicBandCreationDateComparator;
import ru.ifmo.se.runner.Runner;
import ru.ifmo.se.utils.IOManagers.BaseIOManager;
import ru.ifmo.se.utils.IOManagers.IOManager;

import java.io.*;
import java.util.Arrays;

public class App {
    public static void main(String[] args) {
        String filename = "db.xml";


        if (args.length == 0) {
            System.out.println("Используется файл по умолчания " + filename);
        } else {
            filename = args[0];
            System.out.println("Используется файл " + filename);
        }

        FileReader db;
        CollectionManager collectionManager;

        try {
            db = new FileReader(filename);

            collectionManager = CollectionManager.deserialize(db);
        } catch (IOException e) {
            System.out.println(e.getMessage());
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
        commandManager.register("save", new SaveCommand(collectionManager, "test.xml"));
        commandManager.register("add_if_max", new AddIfMaxCommand(collectionManager, new MusicBandCreationDateComparator()));


        //commandManager.register("execute_script", new ExecuteScriptCommand(commandManager));


        Runner runner = new Runner(ioManager, commandManager);
        runner.run();
    }
}
