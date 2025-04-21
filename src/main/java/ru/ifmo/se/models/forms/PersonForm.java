package ru.ifmo.se.models.forms;

import ru.ifmo.se.exceptions.WrongInputException;
import ru.ifmo.se.models.Color;
import ru.ifmo.se.models.Location;
import ru.ifmo.se.models.Person;
import ru.ifmo.se.utils.IOManagers.IOManager;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class PersonForm extends Form<Person> {
    public PersonForm(IOManager ioManager) {
        super(ioManager);
    }

    public Person get() throws WrongInputException, IOException {
        this.ioManager.writeln("{Ввод персоны}");
        String name = this.ioManager.readLine("Введите имя: ");
        ZonedDateTime birthday;
        try {
            String birthdayStr = this.ioManager.readLine("Введите дату рождения (в формате: '2007-12-03 10:15:'): ").replaceAll(" ", "T");

            LocalDateTime localDateTime = LocalDateTime.parse(birthdayStr);
            birthday = localDateTime.atZone(ZoneId.of("Europe/Moscow"));
        } catch (DateTimeException e) {
            throw new WrongInputException("Дата введена не корректно");
        }
        String passportID = this.ioManager.readLine("Введите номер паспорта (не менее 8 цифр): ");
        Color eyeColor = new ColorForm(ioManager).get();
        Location location = new LocationForm(ioManager).get();
        Person result = new Person(name, birthday, passportID, eyeColor, location);
        this.ioManager.writeln("{Ввод персоны закончен}");


        if (result.isValid()) {
            return result;
        }
        throw new WrongInputException("Персона введена не корректно");
    }
}
