package ru.javarush.akursekova.islandtask.animals.plants;
import ru.javarush.akursekova.islandtask.Island;
import ru.javarush.akursekova.islandtask.animals.Viable;

import static ru.javarush.akursekova.islandtask.Island.log;
public class Plant implements Viable {
    protected String emoji;
    protected int weight;

    public Plant() {
        this.emoji = "\uD83C\uDF31";
        this.weight = 1;
    }
    public int weight() {
        return weight;
    }
    public void eatenPlantCleanUp(Island.Location currentLocation){
        log.info("eatenPlantCleanUp started");
        currentLocation.removePlant(this);
        log.debug(this.emoji + "removed from location");
    }

}
