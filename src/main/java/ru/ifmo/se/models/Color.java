package ru.ifmo.se.models;


public enum Color {
    RED,
    BLACK,
    BLUE,
    ORANGE;

    @Override
    public String toString() {
        return switch (this) {
            case RED -> "Color{RED}";
            case BLACK -> "Color{BLACK}";
            case BLUE -> "Color{BLUE}";
            case ORANGE -> "Color{ORANGE}";
        };
    }
}
