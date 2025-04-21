package ru.ifmo.se.models.forms;

import ru.ifmo.se.exceptions.WrongInputException;
import ru.ifmo.se.models.Location;
import ru.ifmo.se.utils.IOManagers.IOManager;

import java.io.IOException;

public class LocationForm extends Form<Location> {
    public LocationForm(IOManager ioManager) {
        super(ioManager);
    }

    public Location get() throws WrongInputException, IOException {
        Double x, y;
        Long z;
        try {
            this.ioManager.writeln("{Ввод локации}");
            x = Double.parseDouble(this.ioManager.readLine("Введите x: "));
            y = Double.parseDouble(this.ioManager.readLine("Введите y: "));
            z = Long.parseLong(this.ioManager.readLine("Введите z: "));
            this.ioManager.writeln("{Ввод локации закончен}");
        } catch (NumberFormatException e) {
            throw new WrongInputException("Числа введены не верно");
        }
        Location result = new Location(x, y, z);
        if (result.isValid()) {
            return result;
        }
        throw new WrongInputException("Локация введена не корректно");
    }

}
