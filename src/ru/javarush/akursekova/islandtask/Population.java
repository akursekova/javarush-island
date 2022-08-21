package ru.javarush.akursekova.islandtask;
import ru.javarush.akursekova.islandtask.animals.carnivore.*;
import ru.javarush.akursekova.islandtask.animals.herbivore.*;

import java.util.HashMap;
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
}
