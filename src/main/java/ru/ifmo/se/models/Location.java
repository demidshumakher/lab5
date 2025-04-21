package ru.ifmo.se.models;


public class Location implements Validatable {
    private Double x; //Поле не может быть null
    private Double y; //Поле не может быть null
    private Long z; //Поле не может быть null

    public Location() {
        this(0.0, 0.0, 0L);
    }

    public Location(Double x, Double y, Long z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Double getX() {
        return this.x;
    }

    public Double getY() {
        return this.y;
    }

    public Long getZ() {
        return this.z;
    }

    public void setX(Double x) {this.x = x;}

    public void setY(Double y) {this.y = y;}

    public void setZ(Long z) {this.z = z;}

    public boolean isValid() {
        return x != null && y != null && z != null;
    }

    @Override
    public String toString() {
        return "Location{ x: " + x + ", y: " + y + ", z: " + z + "}";
    }

}
