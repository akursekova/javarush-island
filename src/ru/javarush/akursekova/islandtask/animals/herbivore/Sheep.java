package ru.javarush.akursekova.islandtask.animals.herbivore;
import ru.javarush.akursekova.islandtask.animals.abstracts.Herbivore;
public class Sheep extends Herbivore {
    public Sheep() {
        weight = 70;
        speed = 3;
        maxFullness = 15;
        currentFullness = maxFullness;
        emoji = "\uD83D\uDC11";
        moved = false;
    }

}
