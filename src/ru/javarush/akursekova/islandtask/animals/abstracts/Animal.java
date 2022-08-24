package ru.javarush.akursekova.islandtask.animals.abstracts;
import ru.javarush.akursekova.islandtask.Island;
import ru.javarush.akursekova.islandtask.RandomNumberGenerator;
import ru.javarush.akursekova.islandtask.animals.Viable;

import java.text.DecimalFormat;
import java.util.Map;

public abstract class Animal implements Viable {
    protected double weight;
    protected int speed;
    protected double maxFullness;
    protected double currentFullness;
    protected String emoji;
    protected boolean moved;
    protected boolean triedToEat;
    protected boolean died;
    protected Map<Class, Integer> foodAndProbability;

    public boolean moved() {
        return moved;
    }
    public double weight() {
        return weight;
    }
    public void setMoved(boolean moved) {
        this.moved = moved;
    }

    public boolean ate() {
        return triedToEat;
    }

    public void setTriedToEat(boolean triedToEat) {
        this.triedToEat = triedToEat;
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

    public void diedAnimalCleanUp(Island.Location currentLocation){
        currentLocation.removeAnimal(this);
        System.out.println("\n" + this + " deleted from location");
    }

    public void reduceFullness(){
        double amountToReduce = maxFullness/4;
        DecimalFormat decimalFormat = new DecimalFormat( "#.###" );
        this.currentFullness = Double.parseDouble(String.valueOf(decimalFormat.format(this.currentFullness - amountToReduce)));
    }

    public void increaseFullness(double weightEatenSpecie){
        this.currentFullness += Math.min(weightEatenSpecie, this.maxFullness - this.currentFullness);
    }
}
