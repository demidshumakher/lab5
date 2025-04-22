package ru.ifmo.se.commands;

import ru.ifmo.se.utils.IOManagers.IOManager;
import java.io.IOException;

/**
 * Command that displays help information about all available commands.
 * This command does not require any arguments.
 */
public class HelpCommand extends Command {
    /**
     * Constructs a new HelpCommand with the name "help" and appropriate description.
     */
    public HelpCommand() {
        super("help", "выводит справку по доступным командам");
    }

    /**
     * Executes the help command, displaying information about all available commands.
     * 
     * @param ioManager the IO manager to use for output
     * @param arguments command arguments (not used for this command)
     * @return true if the command was executed successfully
     * @throws IOException if an I/O error occurs
     */
    @Override
    public boolean execute(IOManager ioManager, String[] arguments) throws IOException {
        ioManager.writeln("""
                Доступные команды:
                help : вывести справку по доступным командам
                info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)
                show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении
                add {element} : добавить новый элемент в коллекцию
                update id {element} : обновить значение элемента коллекции, id которого равен заданному
                remove_by_id id : удалить элемент из коллекции по его id
                clear : очистить коллекцию
                save : сохранить коллекцию в файл
                execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.
                exit : завершить программу (без сохранения в файл)
                remove_at index : удалить элемент, находящийся в заданной позиции коллекции (index)
                remove_last : удалить последний элемент из коллекции
                add_if_max {element} : добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции
                count_by_genre genre : вывести количество элементов, значение поля genre которых равно заданному
                filter_starts_with_name name : вывести элементы, значение поля name которых начинается с заданной подстроки
                filter_less_than_number_of_participants numberOfParticipants : вывести элементы, значение поля numberOfParticipants которых меньше заданного""");
        return true;
    }
}
