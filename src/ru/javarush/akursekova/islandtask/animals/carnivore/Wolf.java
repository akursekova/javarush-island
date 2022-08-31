package ru.javarush.akursekova.islandtask.animals.carnivore;

import ru.javarush.akursekova.islandtask.animals.abstracts.Carnivore;

public class Wolf extends Carnivore {

    public Wolf() {
        weight = 50;
        speed = 3;
        maxFullness = 8;
        currentFullness = maxFullness;
        emoji = "\uD83D\uDC3A";
        moved = false;
        triedToEat = false;
        fertile = true;
    }

}
