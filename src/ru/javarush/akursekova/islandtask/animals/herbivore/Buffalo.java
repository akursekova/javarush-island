package ru.javarush.akursekova.islandtask.animals.herbivore;
import ru.javarush.akursekova.islandtask.animals.abstracts.Herbivore;
public class Buffalo extends Herbivore {
    public Buffalo() {
        weight = 700;
        speed = 2;
        maxFullness = 100;
        currentFullness = maxFullness;
        emoji = "\uD83E\uDDAC";
        moved = false;
    }
}
