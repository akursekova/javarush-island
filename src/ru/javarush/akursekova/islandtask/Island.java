package ru.javarush.akursekova.islandtask;
import ru.javarush.akursekova.islandtask.animals.Viable;
import ru.javarush.akursekova.islandtask.animals.abstracts.Animal;
import ru.javarush.akursekova.islandtask.animals.abstracts.Carnivore;
import ru.javarush.akursekova.islandtask.animals.carnivore.*;
import ru.javarush.akursekova.islandtask.animals.herbivore.*;
import ru.javarush.akursekova.islandtask.animals.plants.Plant;
import ru.javarush.akursekova.islandtask.counter.PopulationCounter;
import ru.javarush.akursekova.islandtask.logging.Logger;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
public class Island {
    public static final Logger log = Logger.getInstance();
    public static final Map<Class, Map<Class, Integer>> FOOD_AND_PROBABILITY = Population.getFoodAndProbability();

    private int length;
    private int width;
    private Location[][] locations;
//    private int maxPlantsInLocations = 200;
private int maxPlantsInLocations = 4;

//todo не забыть убрать если Андрей разрешит использовать поулейшн
public Map<Class, Integer> maxAnimalsInLocation = new HashMap<>() {{
        put(Bear.class, 5);
        //put(Bear.class, 2);
        put(Boa.class, 30);
        //put(Boa.class, 2);
        put(Eagle.class, 20);
        //put(Eagle.class, 2);
        put(Fox.class, 30);
        //put(Fox.class, 2);
        put(Wolf.class, 30);
        //put(Wolf.class, 5);
        put(Boar.class, 50);
        //put(Boar.class, 10);
        put(Buffalo.class, 10);
        //put(Buffalo.class, 2);
        //put(Caterpillar.class, 1000);
        put(Caterpillar.class, 2);
        put(Deer.class, 20);
        //put(Deer.class, 2);
        put(Duck.class, 200);
        //put(Duck.class, 4);
        //put(Goat.class, 140);
        put(Goat.class, 10);
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
        log.debug("\n" + "Island with length = " + length + " and width = " + width + " successfully created.");
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

    //todo надо в качестве параметра передавать не животное а класс. Подумать, может надо вернуть назад на Энимал энимал.
    public boolean animalAmountExceedsLimit(Location location, Animal animal) {
        Class animalClass = animal.getClass();
        int maxAnimalsByClassInLocation = this.maxAnimalsInLocation.get(animalClass);
        //System.out.println(maxAnimalsByTypeInLocation);
        if (location.countAnimalsSameClass(animalClass) + 1 > maxAnimalsByClassInLocation) {
            return true;
        } else {
            return false;
        }
    }
    public Position getPositionLocationToMove(int direction, int iCurrentPosition, int jCurrentPosition) {
        Position positionLocationToMove = null;
        if (direction == Directions.LEFT.direction) {
            positionLocationToMove = new Position(iCurrentPosition, jCurrentPosition - 1);
        }
        if (direction == Directions.RIGHT.direction) {
            positionLocationToMove = new Position(iCurrentPosition, jCurrentPosition + 1);
        }
        if (direction == Directions.UP.direction) {
            positionLocationToMove = new Position(iCurrentPosition - 1, jCurrentPosition);
        }
        if (direction == Directions.DOWN.direction) {
            positionLocationToMove = new Position(iCurrentPosition + 1, jCurrentPosition);
        }
        return positionLocationToMove;
    }
    public boolean locationToMoveIsNotIslandBorder(int direction, int iCurrentPosition, int jCurrentPosition) {
        if (direction == Directions.LEFT.direction) {
            if (this.locations[iCurrentPosition][jCurrentPosition - 1] == null) {
                return false;
            } else {
                return true;
            }
        }
        if (direction == Directions.RIGHT.direction) {
            if (this.locations[iCurrentPosition][jCurrentPosition + 1] == null) {
                return false;
            } else {
                return true;
            }
        }
        if (direction == Directions.UP.direction) {
            if (this.locations[iCurrentPosition - 1][jCurrentPosition] == null) {
                return false;
            } else {
                return true;
            }
        }
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
        log.info("moveAnimals method started");
        for (int i = 1; i < this.getWidth() - 1; i++) {
            for (int j = 1; j < this.getLength() - 1; j++) {
                Location currentLocation = this.getLocation(i, j);
                log.debug("currentLocation = " + currentLocation);
                List<Animal> animals = currentLocation.getAnimalsInLocation();
                for (int k = animals.size() - 1; k >= 0; k--) {
                    Animal currentAnimal = animals.get(k);
                    int iCurrentPosition = i;
                    int jCurrentPosition = j;
                    int iFuturePosition;
                    int jFuturePosition;
                    int[] directionsToMove = currentAnimal.generateDirectionsToMove();

                    //--------------------------//
                    for (int l = 0; l < directionsToMove.length; l++) {
                        log.debug(getDirectionByNumber(directionsToMove[l]) + " ");
                    }
                    log.debug(": animal " + currentAnimal + "\n");
                    //--------------------------//
                    log.debug("current animal = " + currentAnimal
                            + ": moved = " + currentAnimal.moved()
                            + ", tried to eat = " + currentAnimal.triedToEat()
                            + ", fertile = " + currentAnimal.fertile());
                    for (int l = 0; l < directionsToMove.length; l++) {
                        if (currentAnimal.moved() == true) {
                            log.debug(currentAnimal.emoji() + " already moved");
                            continue;
                        } else {
                            Position futurePosition = getPositionLocationToMove(directionsToMove[l], iCurrentPosition, jCurrentPosition);
                            iFuturePosition = futurePosition.getI();
                            jFuturePosition = futurePosition.getJ();
                            if (locationToMoveIsNotIslandBorder(directionsToMove[l], iCurrentPosition, jCurrentPosition)) {
                                Location locationToMove = this.getLocation(iFuturePosition, jFuturePosition);
                                if (animalAmountExceedsLimit(locationToMove, currentAnimal)) {
                                    log.debug("Cannot move: max animals on Location (max = "
                                            + maxAnimalsInLocation.get(currentAnimal.getClass()) + "). " + currentAnimal.emoji()
                                            + " will stay on the same position: ("
                                            + iCurrentPosition + ", " + jCurrentPosition + ").");
                                } else {
                                    //this.changeAnimalPosition(currentLocation, locationToMove, currentAnimal);
                                    locationToMove.addAnimal(currentAnimal);
                                    this.actualizeAnimalsAmountInLocation(locationToMove, currentAnimal, 1);
                                    currentLocation.removeAnimal(currentAnimal);
                                    this.actualizeAnimalsAmountInLocation(currentLocation, currentAnimal, -1);


                                    log.debug(currentAnimal.emoji() + " has been moved "
                                            + getDirectionByNumber(directionsToMove[l])
                                            + " from position (" + iCurrentPosition + ", " + jCurrentPosition
                                            + ") to position (" + iFuturePosition + ", " + jFuturePosition + ") ");
                                    jCurrentPosition = jFuturePosition;
                                    iCurrentPosition = iFuturePosition;
                                    currentLocation = locationToMove;
                                }
                            } else {
                                log.debug("Cannot move: limit of Island. " + currentAnimal.emoji()
                                        + " will stay on the same position: ("
                                        + iCurrentPosition + ", " + jCurrentPosition + ").");
                            }
                        }
                    }
                    currentAnimal.setMoved(true);
                    log.debug("current fullness of " + currentAnimal + "=" + currentAnimal.currentFullness());
                    currentLocation = this.getLocation(i, j);
                }
            }
        }
    }


    public void becomeHungryAfterMovement(){
        log.info("becomeHungryAfterMovement started");
        for (int i = 1; i < this.getWidth() - 1; i++) {
            for (int j = 1; j < this.getLength() - 1; j++) {
                Location currentLocation = this.getLocation(i, j);
                List<Animal> animals = currentLocation.getAnimalsInLocation();
                for (int k = 0; k < animals.size(); k++) {
                    Animal currentAnimal = animals.get(k);
                    currentAnimal.reduceFullness();
                    //--------------------------//
                    log.debug(currentAnimal + " current fullness = " + currentAnimal.currentFullness());
                    //--------------------------//
                }
            }
        }
    }

    public void reproduceNewAnimal(){
        log.info("reproduceNewAnimal started");
        for (int i = 1; i < this.getWidth() - 1; i++) {
            for (int j = 1; j < this.getLength() - 1; j++) {
                Location currentLocation = this.getLocation(i, j);
                List<Animal> animals = currentLocation.getAnimalsInLocation();
                //--------------------------//
                log.debug("location (" + currentLocation.getPosition().getI() + ","
                        + currentLocation.getPosition().getJ() + "): ");
                //--------------------------//
                for (int k = 0; k < animals.size(); k++) {
                    Animal currentAnimal = animals.get(k);
                    if (currentAnimal.fertile() && currentAnimal.getClass().getSimpleName() != "Caterpillar"){
                        //--------------------------//
                        log.debug("current animal: " + currentAnimal);
                        //--------------------------//
                        for (int l = 0; l < animals.size(); l++) {
                            Animal possiblePartner = animals.get(l);
                            if (!(currentAnimal.equals(possiblePartner))
                                    && possiblePartner.getClass() == currentAnimal.getClass()
                                    && !(this.animalAmountExceedsLimit(currentLocation, currentAnimal))
                                    && possiblePartner.fertile() == true){
                                Animal babyAnimal;
                                try {
                                    Constructor<? extends Animal> constructor = currentAnimal.getClass().getConstructor();
                                    babyAnimal = constructor.newInstance();
                                    if (babyAnimal instanceof Carnivore){
                                        PopulationCounter.getInstance().addCarnivore();
                                    } else {
                                        PopulationCounter.getInstance().addHerbivore();
                                    }
                                    babyAnimal.setFertile(false);
                                } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
                                    throw new RuntimeException(e);
                                }
                                //--------------------------//
                                log.debug("new animal appeared " + babyAnimal + " in location ("
                                        + currentLocation.position.getI() + ", "
                                        + currentLocation.position.getJ() + ").");
                                //--------------------------//
                                currentLocation.addAnimal(babyAnimal);
                                this.actualizeAnimalsAmountInLocation(currentLocation, babyAnimal, 1);
                                currentAnimal.setFertile(false);
                                possiblePartner.setFertile(false);
                                break;
                            } else {
                                //--------------------------//
                                log.debug("new baby next time");
                                //--------------------------//
                            }
                        }
                    }
                }
            }
        }
    }

    public void resetFlags(){
        log.info("resetFlags started");
        for (int i = 1; i < this.getWidth() - 1; i++) {
            for (int j = 1; j < this.getLength() - 1; j++) {
                Location currentLocation = this.getLocation(i, j);
                List<Animal> animals = currentLocation.getAnimalsInLocation();
                for (int k = 0; k < animals.size(); k++) {
                    Animal currentAnimal = animals.get(k);
                    currentAnimal.setMoved(false);
                    currentAnimal.setTriedToEat(false);
                    currentAnimal.setFertile(true);
                }
            }
        }
    }

    public void recoverPlants(){
        for (int i = 1; i < this.getWidth() - 1; i++) {
            for (int j = 1; j < this.getLength() - 1; j++) {
                Location currentLocation = this.getLocation(i, j);
                List<Plant> plants = currentLocation.plantsInLocation();
                int oldPlantsAmount = plants.size();
                int newPlantsAmount = maxPlantsInLocations;
                for (int k = 0; k < maxPlantsInLocations - oldPlantsAmount; k++) {
                    Plant plant = new Plant();
                }
            }
        }
    }

    public void massCleanUpFromDiedAnimals(){
        log.info("massCleanUpFromDiedAnimals started");
        for (int i = 1; i < this.getWidth() - 1; i++) {
            for (int j = 1; j < this.getLength() - 1; j++) {
                Location currentLocation = this.getLocation(i, j);
                List<Animal> animals = currentLocation.getAnimalsInLocation();
                for (int k = 0; k < animals.size(); k++) {
                    Animal currentAnimal = animals.get(k);
                    if (currentAnimal.currentFullness() <= 0 &&
                            !(currentAnimal.getClass().getSimpleName().equals("Caterpillar"))){
                        this.actualizeAnimalsAmountInLocation(currentLocation, currentAnimal, -1);
                        currentAnimal.diedAnimalCleanUp(currentLocation);
                        if (currentAnimal instanceof Carnivore){
                            PopulationCounter.getInstance().deleteCarnivore();
                        } else {
                            PopulationCounter.getInstance().deleteHerbivore();
                        }
                        //--------------------------//
                        log.debug(currentAnimal + " died of hunger. Current fullness = " + currentAnimal.currentFullness());
                        //--------------------------//
                    }
                }
            }
        }
    }

    //todo раскомментить генератор вероятности на место
    public void feedAnimals() {
        log.info("feedAnimals started");
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
                log.debug("\n" + "current location (" + currentLocation.position.getI() + ","
                        + currentLocation.position.getJ() + ") before to eat:");
                for (int m = 0; m < animalsReadyToEat.size(); m++) {
                    log.debug(""+animalsReadyToEat.get(m));
                }
                //--------------------------//
                for (int k = animalsReadyToEat.size() - 1; k >= 0 ; k--) {
                    animalReadyToEat = animalsReadyToEat.get(k);
                    if (animalReadyToEat.triedToEat() == true){
                        log.debug(animalReadyToEat + " already ate");
                        continue;
                    }

                    if (animalReadyToEat instanceof Carnivore){
                        candidatesToBeEatenByCarnivore = currentLocation.getAnimalsInLocation();
                        for (int l = candidatesToBeEatenByCarnivore.size() - 1; l >= 0; l--) {
                            Animal candidateToBeEaten = candidatesToBeEatenByCarnivore.get(l);
                            Class candidateToBeEatenClass = candidateToBeEaten.getClass();
                            //todo дождаться что скажет АНдрей
//                            if (!animalReadyToEat.foodAndProbability().containsKey(candidateToBeEatenClass)) {
//                                continue;
//                            }
                            if (!Population.canBeEaten(animalReadyToEat.getClass(), candidateToBeEatenClass)){
                                log.debug(candidateToBeEaten + " cannot be eaten by " + animalReadyToEat);
                                continue;
                            }

                            //todo дождаться что скажет Андрей
                            //probabilityToBeEaten = animalReadyToEat.getProbabilityToBeEaten(candidateToBeEatenClass);
                            probabilityToBeEaten = Population.getProbabilityByClass(animalReadyToEat.getClass(), candidateToBeEatenClass);

                            //todo расскоментить не забыть
                            //RandomNumberGenerator generateProbability = new RandomNumberGenerator();
                            //int randomProbability = generateProbability.getRandomNumber(0, 100);
                            int randomProbability = probabilityToBeEaten;
                            if (randomProbability <= probabilityToBeEaten) {
                                animalReadyToEat.setTriedToEat(true);
                                animalReadyToEat.increaseFullness(candidateToBeEaten.weight());
                                //todo объединить actualizeAnimalsAmountInLocation и die в один метод?
                                this.actualizeAnimalsAmountInLocation(currentLocation, candidateToBeEaten, -1);
                                log.debug("\n" + animalReadyToEat + " ate " + candidateToBeEaten
                                        + " with probability = " + randomProbability);
                                log.debug("current fullness of " + animalReadyToEat + "=" + animalReadyToEat.currentFullness());
                                candidateToBeEaten.diedAnimalCleanUp(currentLocation);
                                break;
                            }
                        }
                    } else {
                        candidatesToBeEatenByHerbivore = currentLocation.generateFoodListForHerbivore();
                        for (int l = candidatesToBeEatenByHerbivore.size() - 1; l >= 0; l--) {
                            Viable candidateToBeEaten = candidatesToBeEatenByHerbivore.get(l);
                            Class candidateToBeEatenClass = candidateToBeEaten.getClass();
                            //todo дождаться что скажет Андрей
//                            if (!animalReadyToEat.foodAndProbability().containsKey(candidateToBeEatenClass)) {
//                                continue;
//                            }
                            if (!Population.canBeEaten(animalReadyToEat.getClass(), candidateToBeEatenClass)){
                                log.debug(candidateToBeEaten + " cannot be eaten by " + animalReadyToEat);
                                continue;
                            }

                            //todo дождаться что скажет Андрей
                            //probabilityToBeEaten = animalReadyToEat.getProbabilityToBeEaten(candidateToBeEatenClass);
                            probabilityToBeEaten = Population.getProbabilityByClass(animalReadyToEat.getClass(), candidateToBeEatenClass);
                            //RandomNumberGenerator generateProbability = new RandomNumberGenerator();
                            //int randomProbability = generateProbability.getRandomNumber(0, 100);
                            int randomProbability = probabilityToBeEaten;
                            if (randomProbability <= probabilityToBeEaten) {
                                animalReadyToEat.setTriedToEat(true);
                                if (candidateToBeEaten instanceof Animal){
                                    Animal animalToBeEaten = (Animal) candidateToBeEaten;
                                    double weightAnimalToBeEaten = animalToBeEaten.weight();
                                    animalReadyToEat.increaseFullness(weightAnimalToBeEaten);
                                    this.actualizeAnimalsAmountInLocation(currentLocation, (Animal) candidateToBeEaten, -1);
                                    animalToBeEaten.diedAnimalCleanUp(currentLocation);
                                }
                                if (candidateToBeEaten instanceof Plant){
                                    Plant plantToBeEaten = (Plant) candidateToBeEaten;
                                    double weightPlantToBeEaten = plantToBeEaten.weight();
                                    animalReadyToEat.increaseFullness(weightPlantToBeEaten);
                                    plantToBeEaten.eatenPlantCleanUp(currentLocation);
                                }

                                log.debug("\n" + animalReadyToEat + " ate " + candidateToBeEaten
                                        + " with probability = " + randomProbability);
                                log.debug("current fullness of " + animalReadyToEat + "=" + animalReadyToEat.currentFullness());
                                break;
                            }
                        }
                    }
                    


                }
                //--------------------------//
                log.debug("current location(" + currentLocation.position.getI() + ","
                        + currentLocation.position.getJ() + ") after to eat:");
                for (int m = 0; m < animalsReadyToEat.size(); m++) {
                    log.debug("" + animalsReadyToEat.get(m));
                }
                log.debug("Left " + currentLocation.plantsInLocation.size() + " plants");
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
        public Map<Class, Integer> animalsAmountByClass() {
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
