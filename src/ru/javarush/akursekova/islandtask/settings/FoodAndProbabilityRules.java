package ru.javarush.akursekova.islandtask.settings;

import ru.javarush.akursekova.islandtask.animals.Viable;
import ru.javarush.akursekova.islandtask.animals.abstracts.Animal;
import ru.javarush.akursekova.islandtask.animals.carnivore.*;
import ru.javarush.akursekova.islandtask.animals.herbivore.*;
import ru.javarush.akursekova.islandtask.animals.plants.Plant;

import java.util.HashMap;
import java.util.Map;

public class FoodAndProbabilityRules {

    private static final Map<Class, Integer> bearFoodAndProbability = new HashMap<>() {{
        put(Boa.class, 80);
        put(Horse.class, 40);
        put(Deer.class, 80);
        put(Rabbit.class, 80);
        put(Mouse.class, 90);
        put(Goat.class, 70);
        put(Sheep.class, 70);
        put(Boar.class, 50);
        put(Buffalo.class, 20);
        put(Duck.class, 10);
    }};

    private static final Map<Class, Integer> boaFoodAndProbability = new HashMap<>() {{
        put(Fox.class, 15);
        put(Rabbit.class, 20);
        put(Mouse.class, 40);
        put(Duck.class, 10);
    }};

    private static final Map<Class, Integer> eagleFoodAndProbability = new HashMap<>() {{
        put(Fox.class, 10);
        put(Rabbit.class, 90);
        put(Mouse.class, 90);
        put(Duck.class, 80);
    }};

    private static final Map<Class, Integer> foxFoodAndProbability = new HashMap<>() {{
        put(Rabbit.class, 70);
        put(Mouse.class, 90);
        put(Duck.class, 60);
        put(Caterpillar.class, 40);
    }};

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

    private static final Map<Class, Integer> boarFoodAndProbability = new HashMap<>() {{
        put(Mouse.class, 50);
        put(Caterpillar.class, 90);
        put(Plant.class, 100);
    }};

    private static final Map<Class, Integer> buffaloFoodAndProbability = new HashMap<>() {{
        put(Plant.class, 100);
    }};

    private static final Map<Class, Integer> caterpillarFoodAndProbability = new HashMap<>() {{
        put(Plant.class, 100);
    }};

    private static final Map<Class, Integer> deerFoodAndProbability = new HashMap<>() {{
        put(Plant.class, 100);
    }};

    private static final Map<Class, Integer> duckFoodAndProbability = new HashMap<>() {{
        put(Caterpillar.class, 90);
        put(Plant.class, 100);
    }};

    private static final Map<Class, Integer> goatFoodAndProbability = new HashMap<>() {{
        put(Plant.class, 100);
    }};

    private static final Map<Class, Integer> horseFoodAndProbability = new HashMap<>() {{
        put(Plant.class, 100);
    }};

    private static final Map<Class, Integer> mouseFoodAndProbability = new HashMap<>() {{
        put(Caterpillar.class, 90);
        put(Plant.class, 100);
    }};

    private static final Map<Class, Integer> rabbitFoodAndProbability = new HashMap<>() {{
        put(Plant.class, 100);
    }};

    private static final Map<Class, Integer> sheepFoodAndProbability = new HashMap<>() {{
        put(Plant.class, 100);
    }};

    private static final Map<Class, Map<Class, Integer>> FOOD_AND_PROBABILITY = new HashMap<>() {{
        put(Bear.class, bearFoodAndProbability);
        put(Boa.class, boaFoodAndProbability);
        put(Eagle.class, eagleFoodAndProbability);
        put(Fox.class, foxFoodAndProbability);
        put(Wolf.class, wolfFoodAndProbability);
        put(Boar.class, boarFoodAndProbability);
        put(Buffalo.class, buffaloFoodAndProbability);
        put(Caterpillar.class, caterpillarFoodAndProbability);
        put(Deer.class, deerFoodAndProbability);
        put(Duck.class, duckFoodAndProbability);
        put(Goat.class, goatFoodAndProbability);
        put(Horse.class, horseFoodAndProbability);
        put(Mouse.class, mouseFoodAndProbability);
        put(Rabbit.class, rabbitFoodAndProbability);
        put(Sheep.class, sheepFoodAndProbability);
    }};

    public Map<Class, Map<Class, Integer>> getFoodAndProbability() {
        return FOOD_AND_PROBABILITY;
    }

    public Integer getProbabilityByClass(Animal readyToEat, Viable toBeEaten) {
        Class readyToEatClass = readyToEat.getClass();
        Class toBeEatenClass = toBeEaten.getClass();
        Map<Class, Integer> foodAndProbabilityByClass = FOOD_AND_PROBABILITY.get(readyToEatClass);
        int probability = foodAndProbabilityByClass.get(toBeEatenClass);
        return probability;
    }
}
