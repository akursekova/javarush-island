package ru.javarush.akursekova.islandtask.animals.carnivore;
import ru.javarush.akursekova.islandtask.animals.abstracts.Carnivore;
public class Boa extends Carnivore {
    public Boa() {
        weight = 15;
        speed = 1;
        maxFullness = 3;
        currentFullness = maxFullness;
        emoji = "\uD83D\uDC0D";
        moved = false;
    }

}
