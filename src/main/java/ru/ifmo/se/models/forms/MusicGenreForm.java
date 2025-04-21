package ru.ifmo.se.models.forms;

import ru.ifmo.se.exceptions.WrongInputException;
import ru.ifmo.se.models.MusicGenre;
import ru.ifmo.se.utils.IOManagers.IOManager;

import java.io.IOException;

public class MusicGenreForm extends Form<MusicGenre> {

    public MusicGenreForm(IOManager ioManager) {
        super(ioManager);
    }

    public MusicGenre get() throws WrongInputException, IOException {
        return switch (this.ioManager.readLine("Введите музыкальный жанр (jazz, rock, post_punk, post_rock): ").toLowerCase()) {
            case "jazz" -> MusicGenre.JAZZ;
            case "rock" -> MusicGenre.ROCK;
            case "post_punk" -> MusicGenre.POST_PUNK;
            case "post_rock" -> MusicGenre.POST_ROCK;
            default -> throw new WrongInputException("Введен неизвестный жанр");
        };
    }
}
