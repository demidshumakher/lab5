package ru.ifmo.se.models;



public enum MusicGenre {
    ROCK,

    JAZZ,

    POST_ROCK,

    POST_PUNK;

    @Override
    public String toString() {
        return switch (this) {
            case ROCK -> "MusicGenre{ROCK}";
            case JAZZ -> "MusicGenre{JAZZ}";
            case POST_PUNK -> "MusicGenre{POST_PUNK}";
            case POST_ROCK -> "MusicGenre{POST_ROCK}";
        };
    }
}
