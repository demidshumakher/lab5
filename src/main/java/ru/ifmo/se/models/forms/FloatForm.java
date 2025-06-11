package ru.ifmo.se.models.forms;

import ru.ifmo.se.models.forms.validators.Validator;
import ru.ifmo.se.utils.IOManagers.IOManager;

import java.io.IOException;
import java.util.Optional;


public class FloatForm extends Form<Float> {
    Validator<Float> check;
    String name;
    public FloatForm(IOManager ioManager, Validator<Float> check, String name) {
        super(ioManager);
        this.check = check;
        this.name = name;
    }

    public Float get() throws IOException {
        Optional<Float> x = Optional.empty();
        while (x.isEmpty()) {
            try {
                x = Optional.of(Float.parseFloat(ioManager.readLine("Введите " + name + ": ")));
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
