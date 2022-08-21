package ru.javarush.akursekova.islandtask.animals.herbivore;
import ru.javarush.akursekova.islandtask.animals.abstracts.Herbivore;
public class Mouse extends Herbivore {
    public Mouse() {
        weight = 0.05;
        speed = 1;
        maxFullness = 0.45;
        currentFullness = maxFullness;
        emoji = "\uD83D\uDC2D";
        moved = false;
    }

}
