package ru.ifmo.se.models.forms;

import ru.ifmo.se.models.forms.validators.Validator;
import ru.ifmo.se.utils.IOManagers.IOManager;

import java.io.IOException;
import java.util.Optional;


public class IntegerForm extends Form<Integer> {
    Validator<Integer> check;
    String name;
    public IntegerForm(IOManager ioManager, Validator<Integer> check, String name) {
        super(ioManager);
        this.check = check;
        this.name = name;
    }

    public Integer get() throws IOException {
        Optional<Integer> x = Optional.empty();
        while (x.isEmpty()) {
            try {
                x = Optional.of(Integer.parseInt(ioManager.readLine("Введите " + name + ": ")));
            } catch (NumberFormatException e) {
                ioManager.writeln("Число введено не верно");
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
