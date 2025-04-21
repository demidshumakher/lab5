package ru.ifmo.se.models.forms;

import ru.ifmo.se.exceptions.WrongInputException;
import ru.ifmo.se.models.Coordinates;
import ru.ifmo.se.utils.IOManagers.IOManager;

import java.io.IOException;

public class CoordinatesForm extends Form<Coordinates> {

    public CoordinatesForm(IOManager ioManager) {
        super(ioManager);
    }

    public Coordinates get() throws WrongInputException, IOException {
        Integer x;
        float y;

        try {
            ioManager.writeln("{Ввод координат}");
            x = Integer.parseInt(this.ioManager.readLine("Введите x: "));
            y = Float.parseFloat(this.ioManager.readLine("Введите y: "));
            ioManager.writeln("{Ввод координат закончен}");
        } catch (NumberFormatException e) {
            throw new WrongInputException("Числа введены не верно");
        }
        Coordinates result = new Coordinates(x, y);
        if (result.isValid()) {
            return result;
        }
        throw new WrongInputException("Введены не корректные координаты");
    }
}
