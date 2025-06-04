package ru.ifmo.se.models.forms;

import ru.ifmo.se.models.forms.validators.Validator;
import ru.ifmo.se.utils.IOManagers.IOManager;

import java.io.IOException;
import java.util.Optional;


public class DoubleForm extends Form<Double> {
    Validator<Double> check;
    String name;
    public DoubleForm(IOManager ioManager, Validator<Double> check, String name) {
        super(ioManager);
        this.check = check;
        this.name = name;
    }

    public Double get() throws IOException {
        Optional<Double> x = Optional.empty();
        while (x.isEmpty()) {
            try {
                x = Optional.of(Double.parseDouble(ioManager.readLine("Введите " + name + ": ")));
            } catch (NumberFormatException | NullPointerException e) {
                ioManager.writeln("Число введено не верно");
                e.printStackTrace();
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
