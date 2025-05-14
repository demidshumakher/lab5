package ru.ifmo.se.models.forms;

import ru.ifmo.se.exceptions.WrongInputException;
import ru.ifmo.se.models.Location;
import ru.ifmo.se.models.forms.validators.NotNullValidator;
import ru.ifmo.se.utils.IOManagers.IOManager;

import java.io.IOException;

public class LocationForm extends Form<Location> {
    public LocationForm(IOManager ioManager) {
        super(ioManager);
    }

    public Location get() throws IOException {
        this.ioManager.writeln("{Ввод локации}");
        Double x = new DoubleForm(ioManager, new NotNullValidator<>(), "x").get();
        Double y = new DoubleForm(ioManager, new NotNullValidator<>(), "y").get();
        Long z = new LongForm(ioManager, new NotNullValidator<>(), "z").get();
        this.ioManager.writeln("{Ввод локации закончен}");
        return new Location(x, y, z);
    }
}
