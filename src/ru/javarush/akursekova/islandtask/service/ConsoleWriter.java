package ru.javarush.akursekova.islandtask.service;
import ru.javarush.akursekova.islandtask.Island;
import ru.javarush.akursekova.islandtask.Population;
import ru.javarush.akursekova.islandtask.animals.abstracts.Carnivore;
import ru.javarush.akursekova.islandtask.counter.PopulationCounter;
import ru.javarush.akursekova.islandtask.animals.abstracts.Animal;

import java.util.List;
import java.util.Map;
public class ConsoleWriter {
    public static final Map<Class, Integer> ANIMALS_POPULATION_CLASSES = Population.getPopulation();
    public static final String CURRENT_ISLAND_STATE = "This is a current state of the Island: ";
    public static final String CURRENT_ISLAND_STATISTICS = "This is a current statistics of the Island: ";
    public static final String TOTAL_CARNIVORES_ISLAND = "Total amount of carnivores: ";
    public static final String TOTAL_HERBIVORES_ISLAND = "Total amount of herbivores: ";
    public static final String TOTAL_ANIMALS_ISLAND = "Total amount of animals: ";
    public static final String TOTAL_PLANTS_ISLAND = "Total amount of plants: ";
    public static final String AMOUNT_OF_CARNIVORES = "Amount of carnivores %s from %d to %d";
    public static final String AMOUNT_OF_HERBIVORES = "Amount of herbivores %s from %d to %d";
    public static final String AMOUNT_DIDNT_CHANGE = "Amount of %s didn't change";
    public static final String AMOUNT_OF_PLANTS = "Amount of plants %s from %d to %d";
    public static final String INCREASED = "increased";
    public static final String DECREASED = "decreased";
    public static final String CARNIVORE = " carnivores ";
    public static final String HERBIVORE = " herbivores ";
    public static final String OPEN_SQUARE_BRACKET = "[";
    public static final String CLOSE_SQUARE_BRACKET = "]";
    public static final String SPACE = " ";
    public static final String NEW_STRING = "\n";
    // TODO: 27/8/22 ждем ответ от Андрея
    //public static final int TOTAL_COUNT_ANIMALS_ON_LOCATION = Population.getTotalCountOfAnimalsOnLocation();
    public void getIslandView(Island island) {
        System.out.println(NEW_STRING + CURRENT_ISLAND_STATE);
        RandomNumberGenerator randomNumber = new RandomNumberGenerator();
        for (int i = 0; i < island.getWidth(); i++) {
            for (int j = 0; j < island.getLength(); j++) {
                Island.Location currentLocation = island.getLocation(i, j);
                if (currentLocation == null) {
                    System.out.print("[\uD83D\uDCA7]" + " ");
                } else {
                    List<Animal> animalsInCurrentLocation = currentLocation.getAnimalsInLocation();
                    int totalAmountOfAnimalInLocation = animalsInCurrentLocation.size();
                    int randomToDisplayAnimal = randomNumber.getRandomNumber(0, totalAmountOfAnimalInLocation - 1);
                    Animal animalToDisplayInConsole = animalsInCurrentLocation.get(randomToDisplayAnimal);
                    System.out.print(OPEN_SQUARE_BRACKET + animalToDisplayInConsole.emoji() + CLOSE_SQUARE_BRACKET + SPACE);
                }
            }
            System.out.println();
        }
    }

    public void getIslandStatistics(Island island) {
        System.out.println(NEW_STRING + CURRENT_ISLAND_STATISTICS);
        System.out.println(TOTAL_HERBIVORES_ISLAND + PopulationCounter.getInstance().getHerbivores());
        System.out.println(TOTAL_CARNIVORES_ISLAND + PopulationCounter.getInstance().getCarnivores());
        System.out.println(TOTAL_ANIMALS_ISLAND + PopulationCounter.getInstance().getTotalAnimals());
        System.out.println(TOTAL_PLANTS_ISLAND + PopulationCounter.getInstance().getPlants());
    }



    public void getDifferenceAnimalAmount(Class animalClass, int oldValue, int newValue){
        if (animalClass.getSimpleName().equals("Carnivore")){
            if (oldValue == newValue){
                System.out.println(String.format(AMOUNT_DIDNT_CHANGE, CARNIVORE));
                return;
            }
            System.out.println(String.format(AMOUNT_OF_CARNIVORES,
                    increasedOrDecreased(oldValue, newValue), oldValue, newValue));
        }
        if (animalClass.getSimpleName().equals("Herbivore")){
            if (oldValue == newValue){
                System.out.println(String.format(AMOUNT_DIDNT_CHANGE, HERBIVORE));
                return;
            }
            System.out.println(String.format(AMOUNT_OF_HERBIVORES,
                    increasedOrDecreased(oldValue, newValue), oldValue, newValue));
        }
    }

    public void getDifferencePlantsAmount(int oldValue, int newValue){
        System.out.println(String.format(AMOUNT_OF_CARNIVORES,
                increasedOrDecreased(oldValue, newValue), oldValue, newValue));
    }

    private String increasedOrDecreased(int oldValue, int newValue){
        if (newValue - oldValue > 0){
            return INCREASED;
        }
        if (newValue - oldValue < 0) {
            return DECREASED;
        } else {
            return "";
        }
    }



    //todo когда мне ответит андрей в этом методе можно выводить на жкран сколько примерно дивотных будет создать мин макс
//    public static void getSummaryAnimalsToBeCreated() {
//
//    }
}
