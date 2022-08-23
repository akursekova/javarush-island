package ru.javarush.akursekova.islandtask.animals.carnivore;
import ru.javarush.akursekova.islandtask.RandomNumberGenerator;
import ru.javarush.akursekova.islandtask.animals.abstracts.Animal;
import ru.javarush.akursekova.islandtask.animals.abstracts.Carnivore;
import ru.javarush.akursekova.islandtask.animals.herbivore.*;

import java.util.Date;
import java.util.HashMap;
public class Wolf extends Carnivore {

    public Wolf() {
        weight = 50;
        speed = 3;
        maxFullness = 8;
        currentFullness = maxFullness;
        emoji = "\uD83D\uDC3A";
        moved = false;
        ate = false;

        foodAndProbability = new HashMap<>(){{
            put(Horse.class, 10);
            put(Deer.class, 15);
            put(Rabbit.class, 60);
            put(Mouse.class, 80);
            put(Goat.class, 60);
            put(Sheep.class, 70);
            put(Boar.class, 15);
            put(Buffalo.class, 10);
            put(Duck.class, 40);
        }};
    }



}
