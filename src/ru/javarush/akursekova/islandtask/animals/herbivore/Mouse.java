package ru.javarush.akursekova.islandtask.animals.herbivore;
import ru.javarush.akursekova.islandtask.animals.abstracts.Herbivore;
import ru.javarush.akursekova.islandtask.animals.plants.Plant;

import java.util.HashMap;
public class Mouse extends Herbivore {
    public Mouse() {
        weight = 0.05;
        speed = 1;
        maxFullness = 0.01;
        currentFullness = maxFullness;
        emoji = "\uD83D\uDC2D";
        moved = false;
        triedToEat = false;

        foodAndProbability = new HashMap<>(){{
            put(Caterpillar.class, 90);
            put(Plant.class, 100);
        }};
    }

}
