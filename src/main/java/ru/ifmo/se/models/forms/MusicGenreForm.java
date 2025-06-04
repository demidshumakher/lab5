package ru.ifmo.se.models.forms;

import ru.ifmo.se.exceptions.WrongInputException;
import ru.ifmo.se.models.Color;
import ru.ifmo.se.models.MusicGenre;
import ru.ifmo.se.utils.IOManagers.IOManager;

import java.io.IOException;
import java.util.Optional;

public class MusicGenreForm extends Form<MusicGenre> {

    public MusicGenreForm(IOManager ioManager) {
        super(ioManager);
    }

    public MusicGenre get() throws IOException {
        Optional<MusicGenre> res = Optional.empty();
        while (res.isEmpty()) {
            try {
                res = switch (this.ioManager.readLine("Введите музыкальный жанр (jazz, rock, post_punk, post_rock): ").toLowerCase()) {
                    case "jazz" -> Optional.of(MusicGenre.JAZZ);
                    case "rock" -> Optional.of(MusicGenre.ROCK);
                    case "post_punk" -> Optional.of(MusicGenre.POST_PUNK);
                    case "post_rock" -> Optional.of(MusicGenre.POST_ROCK);
                    default -> Optional.empty();
                };
                if (res.isEmpty()) {
                    this.ioManager.writeln("Введен неизвестный жанр");
                }
            } catch (NullPointerException e) {
                this.ioManager.writeln("Жанр не может быть null");
            }
        }
        return res.get();
    }
}
