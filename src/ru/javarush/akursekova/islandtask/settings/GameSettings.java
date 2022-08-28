package ru.javarush.akursekova.islandtask.settings;

public class GameSettings {
    //todo TO CORRECT TO 100 x 20
    private int ISLAND_WIDTH = 4;
    private int ISLAND_LENGTH = 4;
    private int ISLAND_WITH_BORDER_WIDTH = ISLAND_WIDTH + 2;
    private int ISLAND_WITH_BORDER_LENGTH = ISLAND_LENGTH + 2;
    private int DAYS_ON_THE_ISLAND = 10;


    public int getIslandWidth() {
        return ISLAND_WIDTH;
    }

    public int getIslandLength() {
        return ISLAND_LENGTH;
    }

    public int getIslandWithBorderWidth() {
        return ISLAND_WITH_BORDER_WIDTH;
    }

    public int getIslandWithBorderLength() {
        return ISLAND_WITH_BORDER_LENGTH;
    }

    public int getDaysOnTheIsland() {
        return DAYS_ON_THE_ISLAND;
    }

}
