package ru.javarush.akursekova.islandtask.animals.abstracts;
import ru.javarush.akursekova.islandtask.Island;
import ru.javarush.akursekova.islandtask.RandomNumberGenerator;

import java.text.DecimalFormat;
import java.util.Map;

public abstract class Animal {
    protected double weight;
    protected int speed;
    protected double maxFullness;
    protected double currentFullness;
    protected String emoji;
    protected boolean moved;
    protected boolean ate;
    protected boolean died;
    protected Map<Class, Integer> foodAndProbability;

    public boolean moved() {
        return moved;
    }

    public void setMoved(boolean moved) {
        this.moved = moved;
    }

    public boolean ate() {
        return ate;
    }

    public void setAte(boolean ate) {
        this.ate = ate;
    }

    public String emoji() {
        return emoji;
    }

    public int speed() {
        return speed;
    }
    public double maxFullness() {
        return maxFullness;
    }

    public double currentFullness() {
        return currentFullness;
    }

    public Map<Class, Integer> foodAndProbability() {
        return foodAndProbability;
    }

    public int getProbabilityToBeEaten(Class clazz){
        return foodAndProbability.get(clazz);
    }

    public int[] generateDirectionsToMove() {
        int[] directionsToMove = new int[speed];
        for (int i = 0; i < directionsToMove.length; i++) {
            RandomNumberGenerator randomNumberGenerator = new RandomNumberGenerator();
            directionsToMove[i] = randomNumberGenerator.getRandomNumber(1,4);
        }
        return directionsToMove;
    }

    public void die(Island.Location currentLocation){
        currentLocation.removeAnimal(this);
    }

    public void reduceFullness(){
        double amountToReduce = maxFullness/4;
        DecimalFormat decimalFormat = new DecimalFormat( "#.###" );
        this.currentFullness = Double.parseDouble(String.valueOf(decimalFormat.format(this.currentFullness - amountToReduce)));
    }
}
