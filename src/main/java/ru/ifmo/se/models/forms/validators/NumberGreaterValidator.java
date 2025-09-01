package ru.ifmo.se.models.forms.validators;

/**
 * Валидатор, который проверяет, что число больше
 */
public class NumberGreaterValidator<T extends Number> extends NotNullValidator<T> {
    private final T value;
    public NumberGreaterValidator(T value){
        this.value = value;
    }

    @Override
    public String description() {
        return super.description() + ". Число должно быть больше " + value;
    }

    public boolean isValid(T val) {
        return super.isValid(val) && val.doubleValue() > value.doubleValue();
    }
}
