package ru.javarush.akursekova.islandtask.animals.herbivore;
import ru.javarush.akursekova.islandtask.animals.abstracts.Herbivore;
import ru.javarush.akursekova.islandtask.animals.plants.Plant;

import java.util.HashMap;
public class Boar extends Herbivore {
    public Boar() {
        weight = 400;
        speed = 2;
        maxFullness = 50;
        currentFullness = 50;
        emoji = "\uD83D\uDC17";
        moved = false;
        triedToEat = false;
        fertile = true;

        foodAndProbability = new HashMap<>(){{
            put(Mouse.class, 50);
            put(Caterpillar.class, 90);
            put(Plant.class, 100);
        }};
    }

}
