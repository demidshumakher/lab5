package ru.ifmo.se.models.forms;

import ru.ifmo.se.exceptions.WrongInputException;
import ru.ifmo.se.models.Color;
import ru.ifmo.se.utils.IOManagers.IOManager;

import java.io.IOException;
import java.util.Optional;

/**
 * Форма ввода для цвета
 */
public class ColorForm extends Form<Color> {

    public ColorForm(IOManager ioManager) {
        super(ioManager);
    }

    public Color get() throws IOException {
        Optional<Color> res = Optional.empty();
        while (res.isEmpty()) {
            try {
                res = switch (this.ioManager.readLine("Введите цвет (red, black, blue, orange): ").toLowerCase()) {
                    case "red" -> Optional.of(Color.RED);
                    case "black" -> Optional.of(Color.BLACK);
                    case "blue" -> Optional.of(Color.BLUE);
                    case "orange" -> Optional.of(Color.ORANGE);
                    default -> Optional.empty();
                };
                if (res.isEmpty()) {
                    this.ioManager.writeln("Введен неизвестный цвет");
                }
            } catch (NullPointerException e) {
                this.ioManager.writeln("Цвет не может быть null");
                e.printStackTrace();
            }
        }

        return res.get();
    }
}
