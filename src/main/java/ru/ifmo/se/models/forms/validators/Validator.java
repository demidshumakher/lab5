package ru.ifmo.se.models.forms.validators;

public interface Validator<T> {
    String description();
    boolean isValid(T val);
}
