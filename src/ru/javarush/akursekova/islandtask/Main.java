package ru.javarush.akursekova.islandtask;
import ru.javarush.akursekova.islandtask.animals.abstracts.Animal;
import ru.javarush.akursekova.islandtask.animals.carnivore.Wolf;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
public class Main {
    public static void main(String[] args) {
        //Island island = new Island(20,100);
        Island island = new Island(4,4);
        Island islandWithBorder = island.buildBorderAroundIsland();

        IslandInitialization islandInitialization = new IslandInitialization();
        islandInitialization.fillCarnivoreAnimals(island);
        islandInitialization.fillHerbivoreAnimals(island);
        islandInitialization.fillPlants(island);

        ConsoleWriter consoleWriter = new ConsoleWriter();
        consoleWriter.getIslandView(island);
        consoleWriter.getIslandStatistics(island);
        consoleWriter.getIslandView(islandWithBorder);

        islandWithBorder.moveAnimals();
//        islandWithBorder.becomeHungryAfterMovement();
//        islandWithBorder.massCleanUpFromDiedAnimals();
        //        System.out.println("animals in location " + islandWithBorder.getLocation(1, 1).getAnimalsInLocation().size());
        //islandWithBorder.feedAnimals();
        //islandWithBorder.moveAnimals();
        for (int i = 1; i < islandWithBorder.getWidth() - 1; i++) {
            for (int j = 1; j < islandWithBorder.getLength() - 1; j++) {
                Island.Location currentLocation = islandWithBorder.getLocation(i, j);
                System.out.println("location (" + currentLocation.getPosition().getI() + "," + currentLocation.getPosition().getJ() + "): ");
                List<Animal> animals = currentLocation.getAnimalsInLocation();
                for (int k = animals.size() - 1; k >= 0; k--) {
                    Animal currentAnimal = animals.get(k);
                    System.out.println(currentAnimal);
                }
            }
        }

        islandWithBorder.reproduceNewAnimal();
    }
}
