package ru.javarush.akursekova.islandtask;

public class Main {
    public static void main(String[] args) {
        Island island = new Island(20,100);
        Island islandWithBorder = island.buildBorderAroundIsland();

        IslandInitialization islandInitialization = new IslandInitialization();
        islandInitialization.fillCarnivoreAnimals(island);
        islandInitialization.fillHerbivoreAnimals(island);

        ConsoleWriter consoleWriter = new ConsoleWriter();
        consoleWriter.getIslandView(island);
        consoleWriter.getIslandStatistics(island);
        consoleWriter.getIslandView(islandWithBorder);

        islandWithBorder.moveAnimals();
    }
}
