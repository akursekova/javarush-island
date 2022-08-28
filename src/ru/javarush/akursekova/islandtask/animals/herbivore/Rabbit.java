package ru.javarush.akursekova.islandtask.animals.herbivore;

import ru.javarush.akursekova.islandtask.animals.abstracts.Herbivore;

public class Rabbit extends Herbivore {
    public Rabbit() {
        weight = 2;
        speed = 2;
        maxFullness = 0.45;
        currentFullness = maxFullness;
        emoji = "\uD83D\uDC07";
        moved = false;
        triedToEat = false;
        fertile = true;
    }
}
