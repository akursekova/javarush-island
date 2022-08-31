package ru.javarush.akursekova.islandtask.animals.herbivore;

import ru.javarush.akursekova.islandtask.animals.abstracts.Herbivore;

public class Caterpillar extends Herbivore {
    public Caterpillar() {
        weight = 0.01;
        speed = 0;
        maxFullness = 0;
        currentFullness = 0;
        emoji = "\uD83D\uDC1B";
        moved = false;
        triedToEat = false;
        fertile = true;
    }

}
