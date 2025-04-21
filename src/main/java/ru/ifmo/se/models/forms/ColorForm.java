package ru.ifmo.se.models.forms;

import ru.ifmo.se.exceptions.WrongInputException;
import ru.ifmo.se.models.Color;
import ru.ifmo.se.utils.IOManagers.IOManager;

import java.io.IOException;


public class ColorForm extends Form<Color> {

    public ColorForm(IOManager ioManager) {
        super(ioManager);
    }

    public Color get() throws WrongInputException, IOException {
        return switch (this.ioManager.readLine("Введите цвет (red, black, blue, orange): ").toLowerCase()) {
            case "red" -> Color.RED;
            case "black" -> Color.BLACK;
            case "blue" -> Color.BLUE;
            case "orange" -> Color.ORANGE;
            default -> throw new WrongInputException("Введен неизвестный цвет");
        };
    }
}
