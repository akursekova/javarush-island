package ru.javarush.akursekova.islandtask;
import ru.javarush.akursekova.islandtask.service.AnimalsInitialization;
import ru.javarush.akursekova.islandtask.service.ProcessManager;
public class Main {
    //private static final Logger log = Logger.getInstance();

    public static void main(String[] args) {
        ProcessManager processManager = new ProcessManager();
        processManager.run();



        //Island island = new Island(20,100);
//        Island island = new Island(4,4);
//        int daysOfLive = 10;



        //Island islandWithBorder = island.buildBorderAroundIsland();
//
//        IslandInitialization islandInitialization = new IslandInitialization();
//        islandInitialization.fillCarnivoreAnimals(island);
//        islandInitialization.fillHerbivoreAnimals(island);
//        islandInitialization.fillPlants(island);
//
//        ConsoleWriter consoleWriter = new ConsoleWriter();
//        consoleWriter.getIslandView(island);
//        consoleWriter.getIslandStatistics(island);
//        consoleWriter.getIslandView(islandWithBorder);
//
//
//        for (int i = 1; i <= daysOfLive; i++) {
//            log.info("day " + i + " started");
//            islandWithBorder.moveAnimals();
//            islandWithBorder.becomeHungryAfterMovement();
//            islandWithBorder.massCleanUpFromDiedAnimals();
//            islandWithBorder.feedAnimals();
//            islandWithBorder.reproduceNewAnimal();
//            islandWithBorder.resetFlags();
//            log.info("day " + i + " finished");
//        }


    }
}
