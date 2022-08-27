package ru.javarush.akursekova.islandtask;
import ru.javarush.akursekova.islandtask.animals.abstracts.Animal;
import ru.javarush.akursekova.islandtask.animals.carnivore.*;
import ru.javarush.akursekova.islandtask.animals.herbivore.*;
import ru.javarush.akursekova.islandtask.animals.plants.Plant;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class Population {
    // todo to decide if to use this class or just the Map from Island.class
    private static final Map<Class, Integer> ANIMALS_POPULATION_CLASSES = new HashMap<>() {{
        put(Bear.class, 5);
        put(Boa.class, 30);
        put(Eagle.class, 20);
        put(Fox.class, 30);
        put(Wolf.class, 30);
        put(Boar.class, 50);
        put(Buffalo.class, 10);
        put(Caterpillar.class, 1000);
        put(Deer.class, 20);
        put(Duck.class, 200);
        put(Goat.class, 140);
        put(Horse.class, 20);
        put(Mouse.class, 500);
        put(Rabbit.class, 150);
        put(Sheep.class, 140);
    }};

    public static Map<Class, Integer> getPopulation(){
        return ANIMALS_POPULATION_CLASSES;
    }

    public static Integer getTotalCountOfAnimalsOnLocation(){
        int totalCountOfAnimalsOnLocation = 0;
        for (Map.Entry entry : ANIMALS_POPULATION_CLASSES.entrySet()){
            totalCountOfAnimalsOnLocation += (int) entry.getValue();
        }
        return totalCountOfAnimalsOnLocation;
    }

    //todo тестовый вариант. Возможно надо перенести в Games Settings

    private static final Map<Class, Integer> wolfFoodAndProbability = new HashMap<>() {{
        put(Horse.class, 10);
        put(Deer.class, 15);
        put(Rabbit.class, 60);
        put(Mouse.class, 80);
        put(Goat.class, 60);
        put(Sheep.class, 70);
        put(Boar.class, 15);
        put(Buffalo.class, 10);
        put(Duck.class, 40);
    }};

    private static final Map<Class, Integer> boarFoodAndProbability = new HashMap<>(){{
        put(Mouse.class, 50);
        put(Caterpillar.class, 90);
        put(Plant.class, 100);
    }};

    private static final Map<Class, Integer> duckFoodAndProbability = new HashMap<>(){{
        put(Caterpillar.class, 90);
        put(Plant.class, 100);
    }};

    private static final Map<Class, Integer> caterpillarFoodAndProbability = new HashMap<>(){{
        put(Plant.class, 100);
    }};

    private static final Map<Class, Map<Class, Integer>> FOOD_AND_PROBABILITY = new HashMap<>(){{
        put(Wolf.class, wolfFoodAndProbability);
        put(Boar.class, boarFoodAndProbability);
        put(Duck.class, duckFoodAndProbability);
        put(Caterpillar.class, caterpillarFoodAndProbability);
    }};

    public static Map<Class, Map<Class, Integer>> getFoodAndProbability(){
        return FOOD_AND_PROBABILITY;
    }

    public static Integer getProbabilityByClass(Class readyToEat, Class toBeEaten){
        Map<Class, Integer> foodAndProbabilityByClass = FOOD_AND_PROBABILITY.get(readyToEat);
        int probability = foodAndProbabilityByClass.get(toBeEaten);
        return probability;
    }

    public static boolean canBeEaten(Class readyToEat, Class toBeEaten){
        Map<Class, Integer> foodAndProbabilityByClass = FOOD_AND_PROBABILITY.get(readyToEat);
        boolean canBeEaten = foodAndProbabilityByClass.containsKey(toBeEaten);
        return canBeEaten;
    }







}
