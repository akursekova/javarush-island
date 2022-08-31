package ru.javarush.akursekova.islandtask.animals.carnivore;

import ru.javarush.akursekova.islandtask.animals.abstracts.Carnivore;

public class Bear extends Carnivore {
    public Bear() {
        weight = 500;
        speed = 2;
        maxFullness = 80;
        currentFullness = maxFullness;
        emoji = "\uD83D\uDC3B";
        moved = false;
        triedToEat = false;
        fertile = true;
    }
}
