package ru.javarush.akursekova.islandtask.animals.herbivore;

import ru.javarush.akursekova.islandtask.animals.abstracts.Herbivore;

public class Goat extends Herbivore {
    public Goat() {
        weight = 60;
        speed = 3;
        maxFullness = 10;
        currentFullness = 10;
        emoji = "\uD83D\uDC10";
        moved = false;
        triedToEat = false;
        fertile = true;
    }

}
