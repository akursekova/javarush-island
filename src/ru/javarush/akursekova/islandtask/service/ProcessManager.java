package ru.javarush.akursekova.islandtask.service;

import ru.javarush.akursekova.islandtask.Island;
import ru.javarush.akursekova.islandtask.counter.StatisticsCounter;
import ru.javarush.akursekova.islandtask.exception.AnimalCreationException;
import ru.javarush.akursekova.islandtask.settings.GameSettings;

import java.util.List;

public class ProcessManager {


    GameSettings gameSettings = new GameSettings();
    AnimalsInitialization animalsInitialization = new AnimalsInitialization();
    ConsoleWriter consoleWriter = new ConsoleWriter();
    StatisticsCounter statisticsCounter = new StatisticsCounter();

    int islandWidth = gameSettings.getIslandWidth();
    int islandLength = gameSettings.getIslandLength();

    List<Integer> statsCollection;


    public void run() {

        Island island = new Island(islandLength, islandWidth);

        animalsInitialization.initialize(island);

        Island islandWithBorder = island.buildBorderAroundIsland();

        consoleWriter.getIslandView(islandWithBorder);
        consoleWriter.getIslandStatistics();

        for (int days = 1; days <= gameSettings.getDaysOnTheIsland(); days++) {

            consoleWriter.dayStarted(days);

            if (days != 1) {
                islandWithBorder.resetFlags();
                consoleWriter.recoverPlants();
                islandWithBorder.recoverPlants();
            }

            consoleWriter.animalsMove();

            islandWithBorder.moveAnimals();

            islandWithBorder.becomeHungryAfterMovement();
            consoleWriter.animalsDied();
            statsCollection = statisticsCounter.start();
            islandWithBorder.massCleanUpFromDiedAnimals();
            statisticsCounter.finish(statsCollection);


            consoleWriter.animalsEat();
            statsCollection = statisticsCounter.start();
            islandWithBorder.feedAnimals();
            statisticsCounter.finish(statsCollection);


            consoleWriter.animalsReproduce();
            statsCollection = statisticsCounter.start();
            try {
                islandWithBorder.reproduceNewAnimal();
            } catch (AnimalCreationException e) {
                System.err.println(e.getMessage());
            }
            statisticsCounter.finish(statsCollection);


            consoleWriter.getIslandStatistics();
            consoleWriter.getIslandView(islandWithBorder);

            consoleWriter.dayFinished(days);
        }
    }
}
