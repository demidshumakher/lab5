package ru.ifmo.se.models;

/**
 * Класс, представляющий координаты объекта в двумерном пространстве.
 * Содержит координаты X и Y с ограничениями на их значения.
 */

public class Coordinates implements Validatable {
    private Integer x; //Поле не может быть null
    private float y; //Значение поля должно быть больше -854

    public Coordinates() {
        this(0, 0);
    }

    public Coordinates(Integer x, float y) {
        this.x = x;
        this.y = y;
    }

    public Integer getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    public void setX(Integer x) {this.x = x;}

    public void setY(float y) {this.y = y;}

    public boolean isValid() {
        return x != null && y > -854;
    }

    @Override
    public String toString() {
        return "Coordinates{ x :" + this.x + ", y:" + this.y + "}";
    }

}

