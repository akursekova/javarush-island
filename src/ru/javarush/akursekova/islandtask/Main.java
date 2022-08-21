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
    public static final Map<Class, Integer> ANIMALS_POPULATION_CLASSES = Population.getPopulation();
    public static void main(String[] args) {
        //Island island = new Island(20,100);
        Island island = new Island(4, 4);

        IslandInitialization islandInitialization = new IslandInitialization();
        islandInitialization.fillCarnivoreAnimals(island);
        islandInitialization.fillHerbivoreAnimals(island);

        ConsoleWriter consoleWriter = new ConsoleWriter();
        //consoleWriter.getIslandView(island);
        consoleWriter.getIslandStatistics(island);

        Island newIsland = island.buildBorderAroundIsland();
        //consoleWriter.getIslandView(newIsland);
        newIsland.moveAnimals();
        newIsland.feedAnimals();

        for (int i = 1; i < newIsland.getWidth() - 1; i++) {
            for (int j = 1; j < newIsland.getLength() - 1; j++) {
                Island.Location currentLocation = newIsland.getLocation(i, j);
                List<Animal> animals = currentLocation.getAnimalsInLocation();
                for (int k = animals.size() - 1; k >= 0; k--) {
                    Animal currentAnimal = animals.get(k);
                    System.out.print(currentAnimal.emoji() + currentAnimal.currentFullness() + " ");
                }
                System.out.println();
            }
        }
    }
}
