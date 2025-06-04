package ru.ifmo.se.models;

/**
 * Перечисление, представляющее различные музыкальные жанры.
 * Содержит константы для распространенных жанров музыки.
 */


public enum MusicGenre {
    ROCK,

    JAZZ,

    POST_ROCK,

    POST_PUNK;

    @Override
    public String toString() {
        return switch (this) {
            case ROCK -> "ROCK";
            case JAZZ -> "JAZZ";
            case POST_PUNK -> "POST_PUNK";
            case POST_ROCK -> "POST_ROCK";
        };
    }
}
