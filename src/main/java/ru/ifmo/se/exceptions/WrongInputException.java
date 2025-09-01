package ru.ifmo.se.exceptions;

/**
 * Выбрасывается, если поступил неверный ввод
 */
public class WrongInputException extends Exception {
    public WrongInputException(String message) {
        super(message);
    }
}
