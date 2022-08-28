package ru.javarush.akursekova.islandtask.service;

import java.util.Random;

public class RandomNumberGenerator {
    public int getRandomNumber(int min, int max) {
        Random random = new Random();
        //todo to remove if works
        //return random.nextInt(min, max);
        return min + (int) (Math.random() * ((max - min) + 1));
    }
}
