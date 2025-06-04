package ru.ifmo.se.commands;

import ru.ifmo.se.collectionManager.CollectionManager;
import ru.ifmo.se.utils.IOManagers.IOManager;
import java.io.IOException;

/**
 * Command that displays information about the collection.
 * This command outputs details such as collection type, initialization date, and number of elements.
 */
public class InfoCommand extends Command {
    /** The collection manager that this command will operate on */
    private final CollectionManager collectionManager;

    /**
     * Constructs a new InfoCommand with the specified collection manager.
     * 
     * @param collectionManager the collection manager to operate on
     */
    public InfoCommand(CollectionManager collectionManager) {
        super("info", "выводит в стандартный поток вывода информацию о коллекции");
        this.collectionManager = collectionManager;
    }

    /**
     * Executes the info command, displaying information about the collection.
     * 
     * @param ioManager the IO manager to use for input/output operations
     * @param arguments the arguments to pass to the command
     * @return true if the command was executed successfully
     * @throws IOException if an I/O error occurs
     */
    @Override
    public boolean execute(IOManager ioManager, String[] arguments) throws IOException {
        ioManager.writeln(this.collectionManager.info());
        return true;
    }


}
