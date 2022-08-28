package ru.javarush.akursekova.islandtask.animals.herbivore;

import ru.javarush.akursekova.islandtask.animals.abstracts.Herbivore;


public class Duck extends Herbivore {
    public Duck() {
        weight = 1;
        speed = 4;
        maxFullness = 0.15;
        currentFullness = maxFullness;
        emoji = "\uD83E\uDD86";
        moved = false;
        triedToEat = false;
        fertile = true;
    }

}
