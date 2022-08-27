package ru.javarush.akursekova.islandtask.settings;
import ru.javarush.akursekova.islandtask.service.AnimalsInitialization;

import java.util.Map;
public class GameSettings {
    private int ISLAND_WIDTH = 4;
    private int ISLAND_LENGTH = 4;
    private int DAYS_ON_THE_ISLAND = 10;


    public int getIslandWidth(){
        return ISLAND_WIDTH;
    }
    public int getIslandLength(){
        return ISLAND_LENGTH;
    }
    public int getDaysOnTheIsland(){
        return DAYS_ON_THE_ISLAND;
    }

}
