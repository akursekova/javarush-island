package ru.javarush.akursekova.islandtask;
import ru.javarush.akursekova.islandtask.animals.Viable;
import ru.javarush.akursekova.islandtask.animals.abstracts.Animal;
import ru.javarush.akursekova.islandtask.animals.abstracts.Carnivore;
import ru.javarush.akursekova.islandtask.animals.carnivore.*;
import ru.javarush.akursekova.islandtask.animals.herbivore.*;
import ru.javarush.akursekova.islandtask.animals.plants.Plant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class Island {
    private int length;
    private int width;
    private Location[][] locations;
//    private int maxPlantsInLocations = 200;
private int maxPlantsInLocations = 12;
    Map<Class, Integer> maxAnimalsInLocation = new HashMap<>() {{
        put(Bear.class, 5);
        //put(Bear.class, 2);
        put(Boa.class, 30);
        //put(Boa.class, 2);
        put(Eagle.class, 20);
        //put(Eagle.class, 2);
        put(Fox.class, 30);
        //put(Fox.class, 2);
        //put(Wolf.class, 30);
        put(Wolf.class, 5);
        //put(Boar.class, 50);
        put(Boar.class, 2);
        put(Buffalo.class, 10);
        //put(Buffalo.class, 2);
        //put(Caterpillar.class, 1000);
        put(Caterpillar.class, 2);
        put(Deer.class, 20);
        //put(Deer.class, 2);
        //put(Duck.class, 200);
        put(Duck.class, 2);
        //put(Goat.class, 140);
        put(Goat.class, 3);
        put(Horse.class, 20);
        //put(Horse.class, 2);
        //put(Mouse.class, 500);
        put(Mouse.class, 2);
        put(Rabbit.class, 150);
        //put(Rabbit.class, 2);
        put(Sheep.class, 140);
        //put(Sheep.class, 2);
    }};
    private enum Directions {
        LEFT(1),
        RIGHT(2),
        UP(3),
        DOWN(4);
        private final int direction;
        Directions(int direction) {
            this.direction = direction;
        }
        public int getDirection() {
            return this.direction;
        }
    }
    public Directions getDirectionByNumber(int direction) {
        for (Directions d : Directions.values()) {
            if (d.getDirection() == direction) {
                return d;
            }
        }
        return null;
    }

    public Island(int length, int width) {
        locations = new Location[width][length];
        this.length = length;
        this.width = width;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < length; j++) {
                locations[i][j] = new Location(new Position(i, j));
            }
        }
        System.out.println("\n" + "Island with length = " + length + " and width = " + width + " successfully created.");
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    public Location getLocation(int i, int j) {
        return locations[i][j];
    }

    public int maxPlantsInLocations() {
        return maxPlantsInLocations;
    }
    public boolean animalAmountExceedsLimit(Location locationToMove, Animal animal) {
        int maxAnimalsByClassInLocation = this.maxAnimalsInLocation.get(animal.getClass());
        //System.out.println(maxAnimalsByTypeInLocation);
        if (locationToMove.countAnimalsSameClass(animal.getClass()) + 1 > maxAnimalsByClassInLocation) {
            return true;
        } else {
            return false;
        }
    }
    public Position getPositionLocationToMove(int direction, int iCurrentPosition, int jCurrentPosition) {
        Position positionLocationToMove = null;
        //left
        if (direction == Directions.LEFT.direction) {
            positionLocationToMove = new Position(iCurrentPosition, jCurrentPosition - 1);
        }
        //right
        if (direction == Directions.RIGHT.direction) {
            positionLocationToMove = new Position(iCurrentPosition, jCurrentPosition + 1);
        }
        //up
        if (direction == Directions.UP.direction) {
            positionLocationToMove = new Position(iCurrentPosition - 1, jCurrentPosition);
        }
        //down
        if (direction == Directions.DOWN.direction) {
            positionLocationToMove = new Position(iCurrentPosition + 1, jCurrentPosition);
        }
        return positionLocationToMove;
    }
    public boolean locationToMoveIsNotIslandBorder(int direction, int iCurrentPosition, int jCurrentPosition) {
        //left
        if (direction == Directions.LEFT.direction) {
            if (this.locations[iCurrentPosition][jCurrentPosition - 1] == null) {
                return false;
            } else {
                return true;
            }
        }
        //right
        if (direction == Directions.RIGHT.direction) {
            if (this.locations[iCurrentPosition][jCurrentPosition + 1] == null) {
                return false;
            } else {
                return true;
            }
        }
        //up
        if (direction == Directions.UP.direction) {
            if (this.locations[iCurrentPosition - 1][jCurrentPosition] == null) {
                return false;
            } else {
                return true;
            }
        }
        //down
        if (direction == Directions.DOWN.direction) {
            if (this.locations[iCurrentPosition + 1][jCurrentPosition] == null) {
                return false;
            } else {
                return true;
            }
        }
        return true;
    }


    public void actualizeAnimalsAmountInLocation(Location location, Animal animal, int coefficient){
        Class animalClass = animal.getClass();
        int oldAmountAnimalsLocation = location.countAnimalsSameClass(animal.getClass());
        int newAmountAnimalsLocation = oldAmountAnimalsLocation + (coefficient) * 1;
        location.setAmountAnimalsInLocation(animalClass, newAmountAnimalsLocation);
    }

    public void moveAnimals() {
        for (int i = 1; i < this.getWidth() - 1; i++) {
            for (int j = 1; j < this.getLength() - 1; j++) {
                Location currentLocation = this.getLocation(i, j);
                List<Animal> animals = currentLocation.getAnimalsInLocation();
                for (int k = animals.size() - 1; k >= 0; k--) {
                    Animal currentAnimal = animals.get(k);
                    int iCurrentPosition = i;
                    int jCurrentPosition = j;
                    int iFuturePosition;
                    int jFuturePosition;
                    int[] directionsToMove = currentAnimal.generateDirectionsToMove();

                    //--------------------------//
                    System.out.println();
                    for (int l = 0; l < directionsToMove.length; l++) {
                        System.out.print(getDirectionByNumber(directionsToMove[l]) + " ");
                    }
                    System.out.println(": animal " + currentAnimal + "\n");
                    //--------------------------//

                    //int[] directionsToMove = {4, 4, 4};
                    for (int l = 0; l < directionsToMove.length; l++) {
                        if (currentAnimal.moved() == true) {
                            System.out.println(currentAnimal.emoji() + " already moved");
                            continue;
                        } else {
                            Position futurePosition = getPositionLocationToMove(directionsToMove[l], iCurrentPosition, jCurrentPosition);
                            iFuturePosition = futurePosition.getI();
                            jFuturePosition = futurePosition.getJ();
                            if (locationToMoveIsNotIslandBorder(directionsToMove[l], iCurrentPosition, jCurrentPosition)) {
                                Location locationToMove = this.getLocation(iFuturePosition, jFuturePosition);
                                if (animalAmountExceedsLimit(locationToMove, currentAnimal)) {
                                    System.out.println("Cannot move: max animals on Location (max = "
                                            + maxAnimalsInLocation.get(currentAnimal.getClass()) + "). " + currentAnimal.emoji()
                                            + " will stay on the same position: ("
                                            + iCurrentPosition + ", " + jCurrentPosition + ").");
                                } else {
                                    //this.changeAnimalPosition(currentLocation, locationToMove, currentAnimal);
                                    locationToMove.addAnimal(currentAnimal);
                                    this.actualizeAnimalsAmountInLocation(locationToMove, currentAnimal, 1);
                                    currentLocation.removeAnimal(currentAnimal);
                                    this.actualizeAnimalsAmountInLocation(currentLocation, currentAnimal, -1);


                                    System.out.println(currentAnimal.emoji() + " has been moved "
                                            + getDirectionByNumber(directionsToMove[l])
                                            + " from position (" + iCurrentPosition + ", " + jCurrentPosition
                                            + ") to position (" + iFuturePosition + ", " + jFuturePosition + ") ");
                                    jCurrentPosition = jFuturePosition;
                                    iCurrentPosition = iFuturePosition;
                                    currentLocation = locationToMove;
                                }
                            } else {
                                System.out.println("Cannot move: limit of Island. " + currentAnimal.emoji()
                                        + " will stay on the same position: ("
                                        + iCurrentPosition + ", " + jCurrentPosition + ").");
                            }
                        }
                    }
                    currentAnimal.setMoved(true);
                    currentAnimal.reduceFullness();
                    System.out.println("current fullness of " + currentAnimal + "=" + currentAnimal.currentFullness());
                    currentLocation = this.getLocation(i, j);
                }
            }
        }
    }



    public void feedAnimals() {
        Animal animalReadyToEat;
        int probabilityToBeEaten;
        for (int i = 1; i < this.getWidth() - 1; i++) {
            for (int j = 1; j < this.getLength() - 1; j++) {
                Location currentLocation = this.getLocation(i, j);
                List<Animal> animalsReadyToEat = currentLocation.getAnimalsInLocation();
                List<Animal> candidatesToBeEatenByCarnivore;
                List<Viable> candidatesToBeEatenByHerbivore;
                //List<Animal> candidatesToBeEatenByCarnivore = currentLocation.getAnimalsInLocation();
                //List<Viable> candidatesToBeEatenByHerbivore = currentLocation.generateFoodListForHerbivore();
                //--------------------------//
                System.out.println("\n" + "current location (" + currentLocation.position.getI() + ","
                        + currentLocation.position.getJ() + ") before to eat:");
                for (int m = 0; m < animalsReadyToEat.size(); m++) {
                    System.out.println(animalsReadyToEat.get(m));
                }
                //--------------------------//
                for (int k = animalsReadyToEat.size() - 1; k >= 0 ; k--) {
                    animalReadyToEat = animalsReadyToEat.get(k);
                    if (animalReadyToEat.ate() == true){
                        System.out.println(animalReadyToEat + " already ate");
                        continue;
                    }

                    if (animalReadyToEat instanceof Carnivore){
                        candidatesToBeEatenByCarnivore = currentLocation.getAnimalsInLocation();
                        for (int l = candidatesToBeEatenByCarnivore.size() - 1; l >= 0; l--) {
                            Animal candidateToBeEaten = candidatesToBeEatenByCarnivore.get(l);
                            Class candidateToBeEatenClass = candidateToBeEaten.getClass();
                            if (!animalReadyToEat.foodAndProbability().containsKey(candidateToBeEatenClass)) {
                                continue;
                            }
                            probabilityToBeEaten = animalReadyToEat.getProbabilityToBeEaten(candidateToBeEatenClass);
                            //RandomNumberGenerator generateProbability = new RandomNumberGenerator();
                            //int randomProbability = generateProbability.getRandomNumber(0, 100);
                            int randomProbability = probabilityToBeEaten;
                            if (randomProbability <= probabilityToBeEaten) {
                                animalReadyToEat.setTriedToEat(true);
                                animalReadyToEat.increaseFullness(candidateToBeEaten);
                                this.actualizeAnimalsAmountInLocation(currentLocation, candidateToBeEaten, -1);
                                System.out.println("\n" + animalReadyToEat + " ate " + candidateToBeEaten
                                        + " with probability = " + randomProbability);
                                System.out.println("current fullness of " + animalReadyToEat + "=" + animalReadyToEat.currentFullness());
                                candidateToBeEaten.die(currentLocation);
                                break;
                            }
                        }
                    } else {
                        candidatesToBeEatenByHerbivore = currentLocation.generateFoodListForHerbivore();
                        for (int l = candidatesToBeEatenByHerbivore.size() - 1; l >= 0; l--) {
                            Viable candidateToBeEaten = candidatesToBeEatenByHerbivore.get(l);
                            Class candidateToBeEatenClass = candidateToBeEaten.getClass();
                            if (!animalReadyToEat.foodAndProbability().containsKey(candidateToBeEatenClass)) {
                                continue;
                            }
                            probabilityToBeEaten = animalReadyToEat.getProbabilityToBeEaten(candidateToBeEatenClass);
                            //RandomNumberGenerator generateProbability = new RandomNumberGenerator();
                            //int randomProbability = generateProbability.getRandomNumber(0, 100);
                            int randomProbability = probabilityToBeEaten;
                            if (randomProbability <= probabilityToBeEaten) {
                                animalReadyToEat.setTriedToEat(true);
                                animalReadyToEat.increaseFullness((Animal) candidateToBeEaten);
                                if (candidateToBeEaten instanceof Animal){
                                    this.actualizeAnimalsAmountInLocation(currentLocation, (Animal) candidateToBeEaten, -1);
                                    ((Animal) candidateToBeEaten).die(currentLocation);
                                }
                                if (candidateToBeEaten instanceof Plant){
                                    ((Plant) candidateToBeEaten).eaten(currentLocation);
                                }

                                System.out.println("\n" + animalReadyToEat + " ate " + candidateToBeEaten
                                        + " with probability = " + randomProbability);
                                System.out.println("current fullness of " + animalReadyToEat + "=" + animalReadyToEat.currentFullness());
                                break;
                            }
                        }
                    }
                    


                }
                //--------------------------//
                System.out.println("current location(" + currentLocation.position.getI() + ","
                        + currentLocation.position.getJ() + ") after to eat:");
                for (int m = 0; m < animalsReadyToEat.size(); m++) {
                    System.out.println(animalsReadyToEat.get(m));
                }
                System.out.println("Left " + currentLocation.plantsInLocation.size() + " plants");
                //--------------------------//
            }
        }
    }
    public Island buildBorderAroundIsland() {
        int newWidth = this.getWidth() + 2;
        int newLength = this.getLength() + 2;
        Island islandWithBorder = new Island(newLength, newWidth);
        for (int i = 0; i < newWidth; i++) {
            for (int j = 0; j < newLength; j++) {
                if (
                        (i == 0 && (j >= 0 && j < newLength)) ||
                                ((i >= 0 && i < newWidth) && j == 0) ||
                                ((i == newWidth - 1) && (j >= 0 && j < newLength)) ||
                                ((i >= 0 && i < newWidth) && (j == newLength - 1))
                ) {
                    islandWithBorder.locations[i][j] = null;
                } else {
                    islandWithBorder.locations[i][j] = this.getLocation(i - 1, j - 1);
                    islandWithBorder.locations[i][j].setPosition(new Position(i, j));
                }
            }
        }
        return islandWithBorder;
    }

    public class Location {
        private Position position;
        private List<Animal> animalsInLocation;
        private Map<Class, Integer> animalsAmountByClass;
        private List<Plant> plantsInLocation;
        public Location(Position position) {
            this.position = position;
            animalsInLocation = new ArrayList<>();
            animalsAmountByClass = new HashMap<>();
            plantsInLocation = new ArrayList<>();
        }
        public Position getPosition() {
            return position;
        }
        public void setPosition(Position position) {
            this.position = position;
        }

        public List<Animal> getAnimalsInLocation() {
            return animalsInLocation;
        }
        public void addAnimal(Animal animal) {
            animalsInLocation.add(animal);
        }
        public void removeAnimal(Animal animal) {
            animalsInLocation.remove(animal);
        }
        public void removePlant(Plant plant){
            plantsInLocation.remove(plant);
        }

        public List<Plant> plantsInLocation() {
            return plantsInLocation;
        }
        public void addPlant(Plant plant) {
            plantsInLocation.add(plant);
        }

        public void setAmountAnimalsInLocation(Class classAnimal, int amountAnimals) {
            animalsAmountByClass.put(classAnimal, amountAnimals);
        }
        public int countAnimalsSameClass(Class clazz) {
            int animalsAmountByClass = this.animalsAmountByClass.get(clazz);
            return animalsAmountByClass;
        }
        
        public List<Viable> generateFoodListForHerbivore(){
            List<Viable> foodListForHerbivore = new ArrayList<>();
            List<Animal> animalsInLocation = this.getAnimalsInLocation();
            for (int i = 0; i < animalsInLocation.size(); i++) {
                if (animalsInLocation.get(i) instanceof Mouse
                        || animalsInLocation.get(i) instanceof Caterpillar){
                    foodListForHerbivore.add(animalsInLocation.get(i));
                }
            }
            foodListForHerbivore.addAll(this.plantsInLocation());
            return foodListForHerbivore;
        }
        
        @Override
        public String toString() {
            return "position=" + position;
        }
    }
}
