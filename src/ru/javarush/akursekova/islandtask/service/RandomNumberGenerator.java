package ru.javarush.akursekova.islandtask.service;

import java.util.Random;

public class RandomNumberGenerator {
    public int getRandomNumber(int min, int max) {
        return min + (int) (Math.random() * ((max - min) + 1));
    }
}
