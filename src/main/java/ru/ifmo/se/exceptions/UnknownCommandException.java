package ru.ifmo.se.exceptions;

/**
 * Выбрасывается, если поступила неизвестная команда
 */
public class UnknownCommandException extends Exception {
    public UnknownCommandException() {
        super("Неизвестная команда");
    }
}
