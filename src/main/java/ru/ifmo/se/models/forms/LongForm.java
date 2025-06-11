package ru.ifmo.se.models.forms;

import ru.ifmo.se.models.forms.validators.Validator;
import ru.ifmo.se.utils.IOManagers.IOManager;

import java.io.IOException;
import java.util.Optional;


public class LongForm extends Form<Long> {
    Validator<Long> check;
    String name;
    public LongForm(IOManager ioManager, Validator<Long> check, String name) {
        super(ioManager);
        this.check = check;
        this.name = name;
    }

    public Long get() throws IOException {
        Optional<Long> x = Optional.empty();
        while (x.isEmpty()) {
            try {
                x = Optional.of(Long.parseLong(ioManager.readLine("Введите " + name + ": ")));
            } catch (NumberFormatException | NullPointerException e) {
                ioManager.writeln("Число введено не верно\n" + e.getMessage() + "\n");
                continue;
            }
            if (!this.check.isValid(x.get())) {
                ioManager.writeln(this.check.description());
                x = Optional.empty();
            }
        }
        return x.get();
    }
}
