package ru.ifmo.se.models.forms;

import ru.ifmo.se.exceptions.WrongInputException;
import ru.ifmo.se.utils.IOManagers.IOManager;

import java.io.IOException;

public abstract class Form<T> {
    protected IOManager ioManager;

    public Form(IOManager ioManager) {
        this.ioManager = ioManager;
    }

    public abstract T get() throws WrongInputException, IOException;
}
