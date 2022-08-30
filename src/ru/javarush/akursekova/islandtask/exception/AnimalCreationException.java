package ru.javarush.akursekova.islandtask.exception;

public class AnimalCreationException extends Exception {
    public AnimalCreationException() {
    }

    public AnimalCreationException(String message) {
        super(message);
    }

    public AnimalCreationException(String message, Throwable cause) {
        super(message, cause);
    }

}
