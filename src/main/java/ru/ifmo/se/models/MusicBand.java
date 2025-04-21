package ru.ifmo.se.models;


import java.util.Date;


public class MusicBand implements Validatable {
    private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private final String name; //Поле не может быть null, Строка не может быть пустой
    private final Coordinates coordinates; //Поле не может быть null
    private Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private final Integer numberOfParticipants; //Поле не может быть null, Значение поля должно быть больше 0
    private final MusicGenre genre; //Поле не может быть null
    private final Person frontMan; //Поле может быть null

    public MusicBand() {
        this.name = null;
        this.coordinates = null;
        this.creationDate = null;
        this.numberOfParticipants = null;
        this.genre = null;
        this.frontMan = null;
    }

    public MusicBand(String name, Coordinates coordinates, Integer numberOfParticipants,
                     Date creationDate ,MusicGenre genre, Person frontMan) {
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.numberOfParticipants = numberOfParticipants;
        this.genre = genre;
        this.frontMan = frontMan;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getName() {
        return this.name;
    }

    public Coordinates getCoordinates() {
        return this.coordinates;
    }

    public Date getCreationDate() {
        return this.creationDate;
    }

    public Integer getNumberOfParticipants() {
        return this.numberOfParticipants;
    }

    public MusicGenre getGenre() {
        return this.genre;
    }

    public Person getFrontMan() {
        return this.frontMan;
    }

    public boolean isValid() {
        if (this.name == null || this.name.isEmpty()) {
            return false;
        }
        if (this.coordinates == null || !this.coordinates.isValid()) {
            return false;
        }
        if (this.creationDate == null) {
            return false;
        }
        if (this.numberOfParticipants == null || this.numberOfParticipants <= 0) {
            return false;
        }
        if (this.genre == null) {
            return false;
        }
        if (this.frontMan != null) {
            return this.frontMan.isValid();
        }
        return true;
    }

    @Override
    public String toString() {
        return "MusicBand{ id: " + id + ", name: " + name + ", coordinates: " + coordinates +
                ", creationDate: " + creationDate + ", numberOfParticipants: " + numberOfParticipants +
                ", genre: " + genre + ", frontMan: " + frontMan + "}";

    }

}
