package ru.ifmo.se.models.forms.validators;

/**
 * Валидатор, который проверяет на не равенство null
 */
public class NotNullValidator<T> implements Validator<T>{
    public NotNullValidator(){}

    public String description() {
        return "Объект не должен быть null";
    }
    public boolean isValid(T val) {
        return val != null;
    }
}
