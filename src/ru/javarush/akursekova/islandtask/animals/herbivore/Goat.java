package ru.javarush.akursekova.islandtask.animals.herbivore;
import ru.javarush.akursekova.islandtask.animals.abstracts.Herbivore;
import ru.javarush.akursekova.islandtask.animals.plants.Plant;

import java.util.HashMap;
public class Goat extends Herbivore {
    public Goat() {
        weight = 60;
        speed = 3;
        maxFullness = 10;
        currentFullness = 10;
        emoji = "\uD83D\uDC10";
        moved = false;
        ate = false;

        foodAndProbability = new HashMap<>(){{
            put(Plant.class, 100);
        }};


    }

}
