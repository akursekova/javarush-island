package ru.javarush.akursekova.islandtask.animals.carnivore;

import ru.javarush.akursekova.islandtask.animals.abstracts.Carnivore;

public class Fox extends Carnivore {
    public Fox() {
        weight = 8;
        speed = 2;
        maxFullness = 3;
        currentFullness = maxFullness;
        emoji = "\uD83E\uDD8A";
        moved = false;
        triedToEat = false;
        fertile = true;
    }

}
