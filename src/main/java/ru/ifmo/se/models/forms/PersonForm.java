package ru.ifmo.se.models.forms;

import ru.ifmo.se.exceptions.WrongInputException;
import ru.ifmo.se.models.Color;
import ru.ifmo.se.models.Location;
import ru.ifmo.se.models.Person;
import ru.ifmo.se.models.forms.validators.StringLenghtValidor;
import ru.ifmo.se.utils.IOManagers.IOManager;

import java.io.IOException;
import java.time.ZonedDateTime;

public class PersonForm extends Form<Person> {
    public PersonForm(IOManager ioManager) {
        super(ioManager);
    }

    public Person get() throws IOException {
        this.ioManager.writeln("{Ввод персоны}");

        String name = new StringForm(ioManager, new StringLenghtValidor(0), "Имя").get();
        String passportID = new StringForm(ioManager, new StringLenghtValidor(7), "номер паспорта (не менее 8 символов)").get();
        Color eyeColor = new ColorForm(ioManager).get();
        Location location = new LocationForm(ioManager).get();
        ZonedDateTime birthday = new ZonedDateTimeForm(this.ioManager).get();


        this.ioManager.writeln("{Ввод персоны закончен}");

        return new Person(name, birthday, passportID, eyeColor, location);
    }
}
