package ru.ifmo.se.exceptions;

public class UnknownCommandException extends Exception {
    public UnknownCommandException() {
        super("Неизвестная команда");
    }
}
