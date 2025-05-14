package ru.ifmo.se.models;

/**
 * Класс, представляющий человека.
 * Содержит информацию о человеке, такую как имя, рост, цвет глаз и волос, а также местоположение.
 * Реализует интерфейс Validatable для проверки валидности данных.
 */


import java.time.ZonedDateTime;

public class Person implements Validatable {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private ZonedDateTime birthday; //Поле может быть null
    private String passportID; //Строка не может быть пустой, Длина строки должна быть не меньше 8, Поле не может быть null
    private Color eyeColor; //Поле может быть null
    private final Location location; //Поле не может быть null

    public Person() {
        this.name = null;
        this.birthday = null;
        this.passportID = null;
        this.eyeColor = null;
        this.location = null;
    }

    public Person(String name, ZonedDateTime birthday, String passportID, Color eyeColor, Location location) {
        this.name = name;
        this.birthday = birthday;
        this.passportID = passportID;
        this.eyeColor = eyeColor;
        this.location = location;
    }

    public String getName() {
        return this.name;
    }

    public ZonedDateTime getBirthday() {
        return this.birthday;
    }

    public String getPassportID() {
        return this.passportID;
    }

    public Color getEyeColor() {
        return this.eyeColor;
    }

    public Location getLocation() {
        return this.location;
    }

    public boolean isValid() {
        if (this.name == null || this.name.isEmpty()) {
            return false;
        }
        if (this.passportID == null || this.passportID.length() < 8) {
            return false;
        }
        return this.location != null && this.location.isValid();
    }

    @Override
    public String toString() {
        return "Person{ name: " + name + ", birthday: " + birthday + ", passportID: " + passportID + ", eyeColor: " + eyeColor + ", location: " + location + "}";
    }


}
