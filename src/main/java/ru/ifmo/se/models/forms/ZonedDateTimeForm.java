package ru.ifmo.se.models.forms;

import ru.ifmo.se.utils.IOManagers.IOManager;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeParseException;
import java.util.Optional;

public class ZonedDateTimeForm extends Form<ZonedDateTime> {
    ZonedDateTimeForm(IOManager ioManager) {
        super(ioManager);
    }

    @Override
    public ZonedDateTime get() throws IOException {
        Optional<ZonedDateTime> birthday = Optional.empty();

        while (birthday.isEmpty()) {
            try {
                String birthdayStr = this.ioManager.readLine("Введите дату рождения (в формате: '2007-12-03 10:15'): ").replaceAll(" ", "T");
                LocalDateTime localDateTime = LocalDateTime.parse(birthdayStr);
                birthday = Optional.of(localDateTime.atZone(ZoneId.of("Europe/Moscow")));
            } catch (DateTimeParseException | NullPointerException e) {
                this.ioManager.writeln("Дата введена не корректно");
                continue;
            }
            if (birthday.get().isAfter(ZonedDateTime.now())) {
                birthday = Optional.empty();
                this.ioManager.writeln("Дата должна быть до настоящего времени");
            }
        }
        return birthday.get();
    }

}
