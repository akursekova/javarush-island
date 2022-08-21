package ru.javarush.akursekova.islandtask.animals.herbivore;
import ru.javarush.akursekova.islandtask.animals.abstracts.Herbivore;
import ru.javarush.akursekova.islandtask.animals.plants.Plant;

import java.util.HashMap;
public class Caterpillar extends Herbivore {
    public Caterpillar() {
        weight = 0.01;
        speed = 0;
        maxFullness = 0;
        currentFullness = 0;
        emoji = "\uD83D\uDC1B";
        moved = false;

        foodAndProbability = new HashMap<>(){{
            put(Plant.class, 100);
        }};

    }

}
