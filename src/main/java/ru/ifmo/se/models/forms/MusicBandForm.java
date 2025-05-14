package ru.ifmo.se.models.forms;

import ru.ifmo.se.exceptions.WrongInputException;
import ru.ifmo.se.models.Coordinates;
import ru.ifmo.se.models.MusicBand;
import ru.ifmo.se.models.MusicGenre;
import ru.ifmo.se.models.Person;
import ru.ifmo.se.models.forms.validators.NumberGreaterValidator;
import ru.ifmo.se.models.forms.validators.StringLenghtValidor;
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
        String name = new StringForm(ioManager, new StringLenghtValidor(0), "имя группы").get();
        Coordinates coordinates = new CoordinatesForm(this.ioManager).get();
        MusicGenre genre = new MusicGenreForm(this.ioManager).get();
        Person frontMan = new PersonForm(this.ioManager).get();
        Integer numberOfParticipants = new IntegerForm(ioManager, new NumberGreaterValidator<>(0),"количество участников").get();
        Date creationDate = new Date();
        this.ioManager.writeln("{Ввод музыкальной банды закончен}");
        return new MusicBand(name, coordinates, numberOfParticipants, creationDate, genre ,frontMan);
    }
}

