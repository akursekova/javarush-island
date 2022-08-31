package ru.javarush.akursekova.islandtask.counter;

import ru.javarush.akursekova.islandtask.animals.abstracts.Carnivore;
import ru.javarush.akursekova.islandtask.animals.abstracts.Herbivore;
import ru.javarush.akursekova.islandtask.animals.plants.Plant;
import ru.javarush.akursekova.islandtask.service.ConsoleWriter;

import java.util.ArrayList;
import java.util.List;

public class StatisticsCounter {
    ConsoleWriter consoleWriter = new ConsoleWriter();

    public List<Integer> start() {
        List<Integer> oldAmountSpecie = new ArrayList<>(3);
        oldAmountSpecie.add(0, PopulationCounter.getInstance().getCarnivores());
        oldAmountSpecie.add(1, PopulationCounter.getInstance().getHerbivores());
        oldAmountSpecie.add(2, PopulationCounter.getInstance().getPlants());
        return oldAmountSpecie;
    }

    public void finish(List<Integer> oldAmountSpecie) {
        List<Integer> newAmountSpecie = new ArrayList<>(3);
        newAmountSpecie.add(0, PopulationCounter.getInstance().getCarnivores());
        newAmountSpecie.add(1, PopulationCounter.getInstance().getHerbivores());
        newAmountSpecie.add(2, PopulationCounter.getInstance().getPlants());

        consoleWriter.compareStats(Carnivore.class, oldAmountSpecie.get(0), newAmountSpecie.get(0));
        consoleWriter.compareStats(Herbivore.class, oldAmountSpecie.get(1), newAmountSpecie.get(1));
        consoleWriter.compareStats(Plant.class, oldAmountSpecie.get(2), newAmountSpecie.get(2));
    }

}
