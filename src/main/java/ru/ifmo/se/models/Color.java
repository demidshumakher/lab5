package ru.ifmo.se.models;

/**
 * Перечисление, представляющее различные цвета.
 * Содержит константы для красного, черного, синего и оранжевого цветов.
 */
public enum Color {
    RED,
    BLACK,
    BLUE,
    ORANGE;

    @Override
    public String toString() {
        return switch (this) {
            case RED -> "RED";
            case BLACK -> "BLACK";
            case BLUE -> "BLUE";
            case ORANGE -> "ORANGE";
        };
    }
}
