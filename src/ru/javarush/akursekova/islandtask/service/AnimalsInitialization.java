package ru.javarush.akursekova.islandtask.service;

import ru.javarush.akursekova.islandtask.Island;
import ru.javarush.akursekova.islandtask.animals.abstracts.Animal;
import ru.javarush.akursekova.islandtask.animals.abstracts.Carnivore;
import ru.javarush.akursekova.islandtask.animals.carnivore.*;
import ru.javarush.akursekova.islandtask.animals.herbivore.*;
import ru.javarush.akursekova.islandtask.animals.plants.Plant;
import ru.javarush.akursekova.islandtask.counter.PopulationCounter;
import ru.javarush.akursekova.islandtask.logging.Logger;
import ru.javarush.akursekova.islandtask.settings.GameSettings;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class AnimalsInitialization {
    public static final Logger log = Logger.getInstance();
    RandomNumberGenerator randomNumberGenerator = new RandomNumberGenerator();
    GameSettings gameSettings = new GameSettings();
    ConsoleWriter consoleWriter = new ConsoleWriter();

    int islandWidth = gameSettings.getIslandWidth();
    int islandLength = gameSettings.getIslandLength();
    int daysOnTheIsland = gameSettings.getDaysOnTheIsland();

    public void initialize(Island island) {
        System.out.println("Welcome to the 'ISLAND' game" + "\n");
        System.out.println("You will observe island life during " + daysOnTheIsland + " days");
        System.out.println("Your Island has size " + islandLength + " x " + islandWidth);
        System.out.println("Starting fields initialization..." + "\n");
        consoleWriter.getSummaryAnimalsToBeCreated(island);
        fillAllSpecies(island);
        System.out.println("Fields are initialized.");
    }

    public void fillAllSpecies(Island island) {
        fillAllAnimals(island);
        System.out.println("\n" + "Carnivores and Herbivores have been created." + "\n");

        fillPlants(island);
        System.out.println("Plants have been created." + "\n");
    }

    public void fillAllAnimals(Island island){
        for (int i = 0; i < island.getWidth(); i++) {
            for (int j = 0; j < island.getLength(); j++) {
                Island.Location currentLocation = island.getLocation(i, j);
                for (Map.Entry  entry : island.maxAnimalsInLocation.entrySet()) {
                    Class animalClass = (Class) entry.getKey();
                    int maxAnimalsInLocation = (int) entry.getValue();
                    //todo revert back
                    //int randomAmountInLocation = randomNumberGenerator.getRandomNumber(0, maxAnimalsInLocation);
                    int randomAmountInLocation = 2;
                    currentLocation.setAmountAnimalsInLocation(animalClass, randomAmountInLocation);
                    for (int k = 0; k < randomAmountInLocation; k++) {
                        Animal newAnimal;
                        try {
                            Constructor<? extends Animal> constructor = animalClass.getConstructor();
                            newAnimal = constructor.newInstance();
                            currentLocation.addAnimal(newAnimal);
                            if (newAnimal instanceof Carnivore) {
                                PopulationCounter.getInstance().addCarnivore();
                            } else {
                                PopulationCounter.getInstance().addHerbivore();
                            }
                        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException |
                                 IllegalAccessException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        }
    }

    public void fillPlants(Island island) {
        System.out.println("Starting to create plants...");
        for (int i = 0; i < island.getWidth(); i++) {
            for (int j = 0; j < island.getLength(); j++) {
                Island.Location currentLocation = island.getLocation(i, j);
                for (int k = 0; k < island.maxPlantsInLocations(); k++) {
                    currentLocation.addPlant(new Plant());
                    PopulationCounter.getInstance().addPlant();
                }
            }
        }
    }
}






