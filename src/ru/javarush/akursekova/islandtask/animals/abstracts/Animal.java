package ru.javarush.akursekova.islandtask.animals.abstracts;

import ru.javarush.akursekova.islandtask.settings.FoodAndProbabilityRules;
import ru.javarush.akursekova.islandtask.animals.plants.Plant;
import ru.javarush.akursekova.islandtask.service.RandomNumberGenerator;
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
    protected boolean fertile;

    FoodAndProbabilityRules foodAndProbabilityRules = new FoodAndProbabilityRules();

    public boolean moved() {
        return moved;
    }

    public double weight() {
        return weight;
    }

    public boolean fertile() {
        return fertile;
    }

    public boolean triedToEat() {
        return triedToEat;
    }

    public void setFertile(boolean fertile) {
        this.fertile = fertile;
    }

    public void setMoved(boolean moved) {
        this.moved = moved;
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

    public int[] generateDirectionsToMove() {
        int[] directionsToMove = new int[speed];
        for (int i = 0; i < directionsToMove.length; i++) {
            RandomNumberGenerator randomNumberGenerator = new RandomNumberGenerator();
            directionsToMove[i] = randomNumberGenerator.getRandomNumber(1, 4);
        }
        return directionsToMove;
    }

    public void reduceFullness() {
        double amountToReduce = maxFullness / 4;
        DecimalFormat decimalFormat = new DecimalFormat("#.###");
        this.currentFullness = Double.parseDouble(String.valueOf(decimalFormat.format(this.currentFullness - amountToReduce)));
    }

    public void increaseFullness(Viable eatenSpecie) {
        double weightEatenSpecie;
        if (eatenSpecie instanceof Animal) {
            Animal eatenAnimal = (Animal) eatenSpecie;
            weightEatenSpecie = eatenAnimal.weight();
        } else {
            Plant eatenPlant = (Plant) eatenSpecie;
            weightEatenSpecie = eatenPlant.weight();
        }
        this.currentFullness += Math.min(weightEatenSpecie, this.maxFullness - this.currentFullness);
    }

    public boolean canEat(Viable toBeEaten) {
        Map<Class, Integer> foodAndProbabilityByClass = foodAndProbabilityRules.getFoodAndProbability().get(this.getClass());
        boolean canEat = foodAndProbabilityByClass.containsKey(toBeEaten.getClass());
        return canEat;
    }

    public boolean meetCriteriaForReproducingChildrenWith(Animal possiblePartner) {
        if (!(this.equals(possiblePartner)) && possiblePartner.getClass() == this.getClass()
                && possiblePartner.fertile() == true) {
            return true;
        } else {
            return false;
        }
    }

}
