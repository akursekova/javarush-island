package ru.javarush.akursekova.islandtask.service;

import ru.javarush.akursekova.islandtask.Island;
import ru.javarush.akursekova.islandtask.animals.abstracts.Carnivore;
import ru.javarush.akursekova.islandtask.animals.abstracts.Herbivore;
import ru.javarush.akursekova.islandtask.animals.plants.Plant;
import ru.javarush.akursekova.islandtask.counter.PopulationCounter;
import ru.javarush.akursekova.islandtask.animals.abstracts.Animal;

import java.util.List;
import java.util.Map;

public class ConsoleWriter {
    private static final String CURRENT_ISLAND_STATE = "This is a current state of the Island: ";
    private static final String CURRENT_ISLAND_STATISTICS = "This is a current statistics of the Island: ";
    private static final String TOTAL_CARNIVORES_ISLAND = "Total amount of carnivores: ";
    private static final String TOTAL_HERBIVORES_ISLAND = "Total amount of herbivores: ";
    private static final String TOTAL_ANIMALS_ISLAND = "Total amount of animals: ";
    private static final String TOTAL_PLANTS_ISLAND = "Total amount of plants: ";
    private static final String AMOUNT_OF_CARNIVORES = "Amount of carnivores %s from %d to %d";
    private static final String AMOUNT_OF_HERBIVORES = "Amount of herbivores %s from %d to %d";
    private static final String AMOUNT_OF_PLANTS = "Amount of plants %s from %d to %d";
    private static final String AMOUNT_DIDNT_CHANGE = "Amount of %s didn't change";
    private static final String WILL_BE_CREATED = "Will be created between 0 and %d %s in each Location";
    private static final String REPRODUCE = "Animals are going to reproduce new animals..";
    private static final String EAT = "Animals are going to eat..";
    private static final String DIED_OF_HUNGER = "Checking if there are animals died of hunger..";
    private static final String MOVE = "Animals are going to move..";
    private static final String RECOVER_PLANTS = "Recovering plants for a new day..";
    private static final String DAY_STARTED = "DAY %d STARTED";
    private static final String DAY_FINISHED = "DAY %d FINISHED";
    private static final String INCREASED = "increased";
    private static final String DECREASED = "decreased";
    private static final String CARNIVORE = "carnivores";
    private static final String HERBIVORE = "herbivores";
    private static final String PLANTS = "plants";
    private static final String OPEN_SQUARE_BRACKET = "[";
    private static final String CLOSE_SQUARE_BRACKET = "]";
    private static final String BAR = "|";
    private static final String SPACE = " ";
    private static final String NEW_STRING = "\n";
    private static final String WATER_EMOTE = "\uD83D\uDCA7";
    private static final String PLANT_EMOTE = "\uD83C\uDF31";

    public void getIslandView(Island island) {
        System.out.println(NEW_STRING + CURRENT_ISLAND_STATE);
        RandomNumberGenerator randomNumber = new RandomNumberGenerator();
        for (int i = 0; i < island.getWidth(); i++) {
            for (int j = 0; j < island.getLength(); j++) {
                Island.Location currentLocation = island.getLocation(i, j);
                if (currentLocation == null) {
                    System.out.print(OPEN_SQUARE_BRACKET + WATER_EMOTE + CLOSE_SQUARE_BRACKET + SPACE);
                } else {
                    List<Animal> animalsInCurrentLocation = currentLocation.getAnimalsInLocation();
                    int totalAmountOfAnimalInLocation = animalsInCurrentLocation.size();
                    if (totalAmountOfAnimalInLocation == 0) {
                        System.out.print(OPEN_SQUARE_BRACKET + PLANT_EMOTE + CLOSE_SQUARE_BRACKET + SPACE);
                        continue;
                    }
                    int randomToDisplayAnimal = randomNumber.getRandomNumber(0, totalAmountOfAnimalInLocation - 1);
                    Animal animalToDisplayInConsole = animalsInCurrentLocation.get(randomToDisplayAnimal);
                    System.out.print(OPEN_SQUARE_BRACKET + animalToDisplayInConsole.emoji() + CLOSE_SQUARE_BRACKET + SPACE);
                }
            }
            System.out.println();
        }
    }

    public void getIslandStatistics() {
        System.out.println(NEW_STRING + CURRENT_ISLAND_STATISTICS);
        System.out.println(TOTAL_HERBIVORES_ISLAND + PopulationCounter.getInstance().getHerbivores());
        System.out.println(TOTAL_CARNIVORES_ISLAND + PopulationCounter.getInstance().getCarnivores());
        System.out.println(TOTAL_ANIMALS_ISLAND + PopulationCounter.getInstance().getTotalAnimals());
        System.out.println(TOTAL_PLANTS_ISLAND + PopulationCounter.getInstance().getPlants());
    }

    public void compareStats(Class clazz, int oldValue, int newValue) {
        if (oldValue == newValue) {
            System.out.println(String.format(AMOUNT_DIDNT_CHANGE, classDecider(clazz)));
            return;
        }
        if (Carnivore.class.isAssignableFrom(clazz)) {
            System.out.println(String.format(AMOUNT_OF_CARNIVORES,
                    increasedOrDecreased(oldValue, newValue), oldValue, newValue));
        }
        if (Herbivore.class.isAssignableFrom(clazz)) {
            System.out.println(String.format(AMOUNT_OF_HERBIVORES,
                    increasedOrDecreased(oldValue, newValue), oldValue, newValue));
        }
        if (Plant.class.isAssignableFrom(clazz)) {
            System.out.println(String.format(AMOUNT_OF_PLANTS,
                    increasedOrDecreased(oldValue, newValue), oldValue, newValue));
        }
    }

    private String classDecider(Class clazz) {
        if (Carnivore.class.isAssignableFrom(clazz)) {
            return CARNIVORE;
        }
        if (Herbivore.class.isAssignableFrom(clazz)) {
            return HERBIVORE;
        } else {
            return PLANTS;
        }
    }

    private String increasedOrDecreased(int oldValue, int newValue) {
        if (newValue - oldValue > 0) {
            return INCREASED;
        }
        if (newValue - oldValue < 0) {
            return DECREASED;
        } else {
            return "";
        }
    }

    public void dayStarted(int dayNumber) {
        System.out.println(NEW_STRING + String.format(DAY_STARTED, dayNumber));
    }

    public void dayFinished(int dayNumber) {
        System.out.println(NEW_STRING + String.format(DAY_FINISHED, dayNumber));
    }

    public void animalsEat() {
        System.out.println(NEW_STRING + EAT);
    }

    public void animalsReproduce() {
        System.out.println(NEW_STRING + REPRODUCE);
    }

    public void animalsDied() {
        System.out.println(NEW_STRING + DIED_OF_HUNGER);
    }

    public void animalsMove() {
        System.out.println(NEW_STRING + MOVE);
    }

    public void recoverPlants() {
        System.out.println(NEW_STRING + RECOVER_PLANTS);
    }

    public void getSummaryAnimalsToBeCreated(Island island) {
        for (Map.Entry entry : island.maxAnimalsInLocation.entrySet()) {
            Class animalClass = (Class) entry.getKey();
            int maxAnimalsInLocation = (int) entry.getValue();
            System.out.println(NEW_STRING + BAR + SPACE + animalClass.getSimpleName().toUpperCase() + SPACE + BAR);
            System.out.println(String.format(WILL_BE_CREATED, maxAnimalsInLocation, animalClass.getSimpleName()));
        }
    }
}
