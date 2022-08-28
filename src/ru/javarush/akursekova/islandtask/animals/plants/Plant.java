package ru.javarush.akursekova.islandtask.animals.plants;

import ru.javarush.akursekova.islandtask.animals.Viable;

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

}
