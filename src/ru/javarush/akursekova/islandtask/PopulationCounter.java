package ru.javarush.akursekova.islandtask;
public class PopulationCounter {

    private static final PopulationCounter INSTANCE = new PopulationCounter();

    public static PopulationCounter getInstance(){
        return INSTANCE;
    }

    int carnivores = 0;
    int herbivores = 0;
    //int plants = 0;

    public void addCarnivore(){
        carnivores++;
    }

    public void addHerbivore(){
        herbivores++;
    }

    //    public void addPlant(){
//        plants++;
//    }
    public int getCarnivores() {
        return carnivores;
    }
    public int getHerbivores() {
        return herbivores;
    }

    public int getTotalAnimals(){
        return herbivores + carnivores;
    }


}
