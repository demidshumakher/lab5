package ru.ifmo.se.models.forms.validators;

/**
 * Интерфейс, который проверяет форму
 */
public interface Validator<T> {
    String description();
    boolean isValid(T val);
}
