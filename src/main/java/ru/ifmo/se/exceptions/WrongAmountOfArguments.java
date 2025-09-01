package ru.ifmo.se.exceptions;

/**
 * Выбрасывается, если поступило неверное количество аргументов
 */
public class WrongAmountOfArguments extends Exception {
    public WrongAmountOfArguments() {
        super("Неверное количество аргументов");
    }
}
