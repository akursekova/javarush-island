package ru.javarush.akursekova.islandtask;
import ru.javarush.akursekova.islandtask.animals.abstracts.Animal;
import ru.javarush.akursekova.islandtask.animals.carnivore.Boa;
import ru.javarush.akursekova.islandtask.animals.carnivore.Fox;
import ru.javarush.akursekova.islandtask.animals.carnivore.Wolf;
import ru.javarush.akursekova.islandtask.animals.herbivore.*;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class Main {
    public static void main(String[] args) {
        Island island = new Island(20,100);
        Island islandWithBorder = island.buildBorderAroundIsland();

        IslandInitialization islandInitialization = new IslandInitialization();
        islandInitialization.fillCarnivoreAnimals(island);
        islandInitialization.fillHerbivoreAnimals(island);

        ConsoleWriter consoleWriter = new ConsoleWriter();
        consoleWriter.getIslandView(island);
        consoleWriter.getIslandStatistics(island);
        consoleWriter.getIslandView(islandWithBorder);

        islandWithBorder.moveAnimals();
    }
}
