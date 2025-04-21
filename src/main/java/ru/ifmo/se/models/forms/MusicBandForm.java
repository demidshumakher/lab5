package ru.ifmo.se.models.forms;

import ru.ifmo.se.exceptions.WrongInputException;
import ru.ifmo.se.models.Coordinates;
import ru.ifmo.se.models.MusicBand;
import ru.ifmo.se.models.MusicGenre;
import ru.ifmo.se.models.Person;
import ru.ifmo.se.utils.IOManagers.IOManager;

import javax.imageio.IIOException;
import java.io.IOException;
import java.util.Date;

public class MusicBandForm extends Form<MusicBand> {
    public MusicBandForm(IOManager ioManager) {
        super(ioManager);
    }

    public MusicBand get() throws WrongInputException, IOException {
        this.ioManager.writeln("{Ввод музыкальной банды}");
        String name = this.ioManager.readLine("Введите имя группы: ");
        Coordinates coordinates = new CoordinatesForm(this.ioManager).get();
        Date creationDate = new Date();
        Integer numberOfParticipants = Integer.parseInt(this.ioManager.readLine("Введите количество участников: "));
        MusicGenre genre = new MusicGenreForm(this.ioManager).get();
        Person frontMan = new PersonForm(this.ioManager).get();
        MusicBand result = new MusicBand(name, coordinates, numberOfParticipants, creationDate, genre ,frontMan);
        this.ioManager.writeln("{Ввод музыкальной банды закончен}");
        if (result.isValid()) {
            return result;
        }
        throw new WrongInputException("Музыкальная группа введена не корректно");
    }
}

