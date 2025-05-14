package ru.ifmo.se.models.forms;

import ru.ifmo.se.models.forms.validators.Validator;
import ru.ifmo.se.utils.IOManagers.IOManager;

import java.io.IOException;
import java.util.Optional;


public class StringForm extends Form<String> {
    Validator<String> check;
    String name;
    public StringForm(IOManager ioManager, Validator<String> check, String name) {
        super(ioManager);
        this.check = check;
        this.name = name;
    }

    public String get() throws IOException {
        Optional<String> x = Optional.empty();
        while (x.isEmpty()) {
            x = Optional.of(ioManager.readLine("Введите " + name + ": "));
            if (!this.check.isValid(x.get())) {
                ioManager.writeln(this.check.description());
                x = Optional.empty();
            }
        }
        return x.get();
    }
}
