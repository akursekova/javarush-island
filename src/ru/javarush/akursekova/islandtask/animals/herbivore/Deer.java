package ru.javarush.akursekova.islandtask.animals.herbivore;

import ru.javarush.akursekova.islandtask.animals.abstracts.Herbivore;

public class Deer extends Herbivore {
    public Deer() {
        weight = 300;
        speed = 4;
        maxFullness = 50;
        currentFullness = maxFullness;
        emoji = "\uD83E\uDD8C";
        moved = false;
        triedToEat = false;
        fertile = true;
        ;
    }

}
