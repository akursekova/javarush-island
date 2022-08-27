package ru.javarush.akursekova.islandtask.service;
import ru.javarush.akursekova.islandtask.Island;
import ru.javarush.akursekova.islandtask.animals.abstracts.Carnivore;
import ru.javarush.akursekova.islandtask.animals.abstracts.Herbivore;
import ru.javarush.akursekova.islandtask.counter.PopulationCounter;
import ru.javarush.akursekova.islandtask.settings.GameSettings;
public class ProcessManager {
    GameSettings gameSettings = new GameSettings();
    AnimalsInitialization animalsInitialization = new AnimalsInitialization();
    ConsoleWriter consoleWriter = new ConsoleWriter();

    int islandWidth = gameSettings.getIslandWidth();
    int islandLength = gameSettings.getIslandLength();

    int carnivoresAmountOld, carnivoresAmountNew, herbivoresAmountOld, herbivoresAmountNew;


    public void run(){
        Island island = new Island(islandLength, islandWidth);

        animalsInitialization.initialize(island);

        Island islandWithBorder = island.buildBorderAroundIsland();

        consoleWriter.getIslandView(islandWithBorder);
        consoleWriter.getIslandStatistics(islandWithBorder);

        islandWithBorder.moveAnimals();
        islandWithBorder.becomeHungryAfterMovement();

        carnivoresAmountOld = PopulationCounter.getInstance().getCarnivores();
        herbivoresAmountOld = PopulationCounter.getInstance().getHerbivores();
        islandWithBorder.massCleanUpFromDiedAnimals();
        carnivoresAmountNew = PopulationCounter.getInstance().getCarnivores();
        herbivoresAmountNew = PopulationCounter.getInstance().getHerbivores();
        consoleWriter.getDifferenceAnimalAmount(Carnivore.class, carnivoresAmountOld, carnivoresAmountNew);
        consoleWriter.getDifferenceAnimalAmount(Herbivore.class, herbivoresAmountOld, herbivoresAmountNew);

        islandWithBorder.feedAnimals();

        carnivoresAmountOld = PopulationCounter.getInstance().getCarnivores();
        herbivoresAmountOld = PopulationCounter.getInstance().getHerbivores();
        islandWithBorder.reproduceNewAnimal();
        carnivoresAmountNew = PopulationCounter.getInstance().getCarnivores();
        herbivoresAmountNew = PopulationCounter.getInstance().getHerbivores();
        consoleWriter.getDifferenceAnimalAmount(Carnivore.class, carnivoresAmountOld, carnivoresAmountNew);
        consoleWriter.getDifferenceAnimalAmount(Herbivore.class, herbivoresAmountOld, herbivoresAmountNew);

        islandWithBorder.resetFlags();


    }








}
