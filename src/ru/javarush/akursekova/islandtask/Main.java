package ru.javarush.akursekova.islandtask;
import ru.javarush.akursekova.islandtask.animals.abstracts.Animal;
import ru.javarush.akursekova.islandtask.animals.plants.Plant;

import java.util.ArrayList;
import java.util.List;
public class Main {
    public static void main(String[] args) {
        //Island island = new Island(20,100);
        Island island = new Island(4,4);
        Island islandWithBorder = island.buildBorderAroundIsland();

        IslandInitialization islandInitialization = new IslandInitialization();
        islandInitialization.fillCarnivoreAnimals(island);
        islandInitialization.fillHerbivoreAnimals(island);
        //islandInitialization.fillPlants(island);

        ConsoleWriter consoleWriter = new ConsoleWriter();
        consoleWriter.getIslandView(island);
        consoleWriter.getIslandStatistics(island);
        consoleWriter.getIslandView(islandWithBorder);

        islandWithBorder.moveAnimals();
//        System.out.println("animals in location " + islandWithBorder.getLocation(1, 1).getAnimalsInLocation().size());
        islandWithBorder.feedAnimals();


    }
}
