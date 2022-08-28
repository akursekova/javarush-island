package ru.javarush.akursekova.islandtask.animals.herbivore;

import ru.javarush.akursekova.islandtask.animals.abstracts.Herbivore;

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
    }
}
