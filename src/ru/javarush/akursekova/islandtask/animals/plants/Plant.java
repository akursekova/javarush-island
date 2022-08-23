package ru.javarush.akursekova.islandtask.animals.plants;
import ru.javarush.akursekova.islandtask.Island;
import ru.javarush.akursekova.islandtask.animals.Viable;
public class Plant implements Viable {
    protected String emoji;
    public Plant() {
        this.emoji = "\uD83C\uDF31";
    }

    public void eaten(Island.Location currentLocation){
        currentLocation.removePlant(this);
    }

}
