package ru.javarush.akursekova.islandtask.animals.carnivore;
import ru.javarush.akursekova.islandtask.animals.abstracts.Carnivore;
public class Eagle extends Carnivore {
    public Eagle() {
        weight = 6;
        speed = 3;
        maxFullness = 1;
        currentFullness = maxFullness;
        emoji = "\uD83E\uDD85";
        moved = false;
        ate = false;
    }

}
