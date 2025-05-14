package ru.ifmo.se.models.forms;

import ru.ifmo.se.exceptions.WrongInputException;
import ru.ifmo.se.models.Coordinates;
import ru.ifmo.se.models.forms.validators.NotNullValidator;
import ru.ifmo.se.models.forms.validators.NumberGreaterValidator;
import ru.ifmo.se.utils.IOManagers.IOManager;

import java.io.IOException;

public class CoordinatesForm extends Form<Coordinates> {

    public CoordinatesForm(IOManager ioManager) {
        super(ioManager);
    }

    public Coordinates get() throws IOException {
        ioManager.writeln("{Ввод координат}");
        Integer x = new IntegerForm(ioManager, new NotNullValidator<Integer>(), "x").get();
        float y = new FloatForm(ioManager, new NumberGreaterValidator<Float>(-854f), "y").get();
        ioManager.writeln("{Ввод координат закончен}");
        return new Coordinates(x, y);
    }
}
