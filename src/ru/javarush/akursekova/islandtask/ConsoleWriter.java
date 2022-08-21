package ru.javarush.akursekova.islandtask;
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
    public static final String OPEN_SQUARE_BRACKET = "[";
    public static final String CLOSE_SQUARE_BRACKET = "]";
    public static final String SPACE = " ";
    public static final String NEW_STRING = "\n";
    public static final int TOTAL_COUNT_ANIMALS_ON_LOCATION = Population.getTotalCountOfAnimalsOnLocation();
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
    }
    // я не знаю как вытащить значение поля из конструктора класса
//    public static void getSummaryAnimalsToBeCreated() {
//        for (Map.Entry entry : ANIMALS_POPULATION_CLASSES.entrySet()) {
//            Class currentClass = (Class) entry.getKey();
//            String parentClassName = currentClass.getSuperclass().getSimpleName();
//            int maxCountOfAnimals = (int) entry.getValue();
//            if (parentClassName.equals("Carnivore")) {
//                System.out.println("Will be created " + maxCountOfAnimals + " " + currentClass.getSimpleName() + "s");
//                //System.out.println(NEW_STRING);
//            } else {
//                System.out.println("Will be created " + maxCountOfAnimals + " " + currentClass.getSimpleName() + "s");
//                //System.out.println(NEW_STRING);
//            }
//        }
//    }
}
