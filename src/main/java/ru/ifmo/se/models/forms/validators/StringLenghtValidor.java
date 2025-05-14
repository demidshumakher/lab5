package ru.ifmo.se.models.forms.validators;

public class StringLenghtValidor extends NotNullValidator<String> {
    private final int length;
    public StringLenghtValidor(int length){
        this.length = length;
    }
    @Override
    public String description() {
        return super.description() + ". Длина должна быть больше " + length;
    }
    @Override
    public boolean isValid(String val) {
        return super.isValid(val) && val.length() > length;
    }
}
