package ru.ifmo.se.commands;

import ru.ifmo.se.collectionManager.CollectionManager;
import ru.ifmo.se.exceptions.WrongAmountOfArguments;
import ru.ifmo.se.utils.IOManagers.IOManager;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Command that saves the collection to a file.
 * This command serializes the collection and writes it to the specified file.
 * This command requires exactly one argument: the command name.
 */
public class SaveCommand extends Command {

    /** The collection manager that this command will operate on */
    private final CollectionManager collectionManager;

    /** The name of the file to save the collection to */
    private final String fileName;

    /**
     * Constructs a new SaveCommand with the specified collection manager and file name.
     * 
     * @param collectionManager the collection manager to operate on
     * @param fileName the name of the file to save the collection to
     */
    public SaveCommand(CollectionManager collectionManager, String fileName) {
        super("save", "сохраняет коллекцию");
        this.collectionManager = collectionManager;
        this.fileName = fileName;
    }

    /**
     * Executes the save command, serializing the collection and writing it to the file.
     * 
     * @param ioManager the IO manager to use for input/output operations
     * @param arguments the arguments to pass to the command (should be just the command name)
     * @return true if the command was executed successfully
     * @throws WrongAmountOfArguments if the number of arguments is not 1
     * @throws IOException if an I/O error occurs while writing to the file
     */
    @Override
    public boolean execute(IOManager ioManager, String[] arguments) throws WrongAmountOfArguments, IOException {
        if (arguments.length != 1) {
            throw new WrongAmountOfArguments();
        }


        FileWriter fw = new FileWriter(this.fileName);

        this.collectionManager.serialize(fw);
        return true;


    }
}
