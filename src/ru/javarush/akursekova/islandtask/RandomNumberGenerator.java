package ru.javarush.akursekova.islandtask;
import java.util.Random;
public class RandomNumberGenerator {
    public int getRandomNumber(int min, int max){
        Random random = new Random();
        return random.nextInt(min, max);
    }
}
