package ru.ifmo.se.models.forms.validators;

public class NotNullValidator<T> implements Validator<T>{
    public NotNullValidator(){}

    public String description() {
        return "Объект не должен быть null";
    }
    public boolean isValid(T val) {
        return val != null;
    }
}
