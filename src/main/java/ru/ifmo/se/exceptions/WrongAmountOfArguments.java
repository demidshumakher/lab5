package ru.ifmo.se.exceptions;

public class WrongAmountOfArguments extends Exception {
    public WrongAmountOfArguments() {
        super("Неверное количество аргументов");
    }
}
