package ru.javarush.akursekova.islandtask.animals.herbivore;

import ru.javarush.akursekova.islandtask.animals.abstracts.Herbivore;

public class Horse extends Herbivore {
    public Horse() {
        weight = 400;
        speed = 4;
        maxFullness = 60;
        currentFullness = maxFullness;
        emoji = "\uD83D\uDC34";
        moved = false;
        triedToEat = false;
        fertile = true;
    }

}
