package ru.javarush.akursekova.islandtask;

import ru.javarush.akursekova.islandtask.animals.Viable;
import ru.javarush.akursekova.islandtask.animals.abstracts.Animal;
import ru.javarush.akursekova.islandtask.animals.abstracts.Carnivore;
import ru.javarush.akursekova.islandtask.animals.abstracts.Herbivore;
import ru.javarush.akursekova.islandtask.animals.carnivore.*;
import ru.javarush.akursekova.islandtask.animals.herbivore.*;
import ru.javarush.akursekova.islandtask.animals.plants.Plant;
import ru.javarush.akursekova.islandtask.counter.PopulationCounter;
import ru.javarush.akursekova.islandtask.logging.Logger;
import ru.javarush.akursekova.islandtask.service.RandomNumberGenerator;
import ru.javarush.akursekova.islandtask.settings.GameSettings;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class Island {
    public static final Logger log = Logger.getInstance();
    GameSettings gameSettings = new GameSettings();
    FoodAndProbabilityRules foodAndProbabilityRules = new FoodAndProbabilityRules();
    RandomNumberGenerator generateProbability = new RandomNumberGenerator();

    private int length;
    private int width;
    private Location[][] locations;
    //todo don't forget to return correct value
//    private int maxPlantsInLocations = 200;
    private int maxPlantsInLocations = 4;


    //todo todo don't forget to return correct value
    public Map<Class, Integer> maxAnimalsInLocation = new HashMap<>() {{
        //put(Bear.class, 5);
        put(Bear.class, 2);
        //put(Boa.class, 30);
        put(Boa.class, 2);
        //put(Eagle.class, 20);
        put(Eagle.class, 2);
        //put(Fox.class, 30);
        put(Fox.class, 2);
        //put(Wolf.class, 30);
        put(Wolf.class, 5);
        //put(Boar.class, 50);
        put(Boar.class, 2);
        //put(Buffalo.class, 10);
        put(Buffalo.class, 2);
        //put(Caterpillar.class, 1000);
        put(Caterpillar.class, 2);
        //put(Deer.class, 20);
        put(Deer.class, 2);
        //put(Duck.class, 200);
        put(Duck.class, 2);
        //put(Goat.class, 140);
        put(Goat.class, 2);
        //put(Horse.class, 20);
        put(Horse.class, 2);
        //put(Mouse.class, 500);
        put(Mouse.class, 2);
        //put(Rabbit.class, 150);
        put(Rabbit.class, 2);
        //put(Sheep.class, 140);
        put(Sheep.class, 2);
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
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < length; y++) {
                locations[x][y] = new Location(new Position(x, y));
            }
        }
        log.debug("Island with length = " + length + " and width = " + width + " successfully created.");
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    public Location getLocation(int x, int y) {
        return locations[x][y];
    }

    public int maxPlantsInLocations() {
        return maxPlantsInLocations;
    }


    public boolean animalAmountExceedsLimit(Location location, Animal animal) {
        Class animalClass = animal.getClass();
        int maxAnimalsByClassInLocation = this.maxAnimalsInLocation.get(animalClass);
        if (location.amountAnimalsByClass(animalClass) + 1 > maxAnimalsByClassInLocation) {
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

    public int calculatePosition(int position, int coefficient){
        return position + coefficient;
    }

    //todo check with debug if works correctly (to use instead of getPositionLocationToMove)
    public Position getPositionLocationToMoveTEST(int direction, int xCurrentPosition, int yCurrentPosition) {
        if (direction == 1) {
            yCurrentPosition = calculatePosition(yCurrentPosition, -1);
        }
        if (direction == 2) {
            yCurrentPosition = calculatePosition(yCurrentPosition, 1);
        }
        if (direction == 3) {
            xCurrentPosition = calculatePosition(xCurrentPosition, -1);
        }
        if (direction == 4) {
            xCurrentPosition = calculatePosition(xCurrentPosition, 1);
        }

        return new Position(xCurrentPosition, yCurrentPosition);
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
                    int xCurrentPosition = i;
                    int yCurrentPosition = j;
                    int xFuturePosition;
                    int yFuturePosition;
                    int[] directionsToMove = currentAnimal.generateDirectionsToMove();

                    //--------------------------//
                    for (int l = 0; l < directionsToMove.length; l++) {
                        log.debug(getDirectionByNumber(directionsToMove[l]) + " ");
                    }
                    log.debug(": animal " + currentAnimal + "\n");
                    //--------------------------//
                    log.debug("current animal = " + currentAnimal + ": moved = " + currentAnimal.moved() + ", tried to eat = " + currentAnimal.triedToEat() + ", fertile = " + currentAnimal.fertile());
                    for (int l = 0; l < directionsToMove.length; l++) {
                        if (currentAnimal.moved() == true) {
                            log.debug(currentAnimal.emoji() + " already moved");
                            continue;
                        } else {
                            Position futurePosition = getPositionLocationToMove(directionsToMove[l], xCurrentPosition, yCurrentPosition);
                            xFuturePosition = futurePosition.getX();
                            yFuturePosition = futurePosition.getY();
                            //todo to think maybe I still can improve it
                            if (!locationIsOnBorder(xFuturePosition, yFuturePosition, gameSettings.getIslandWithBorderWidth(), gameSettings.getIslandWithBorderLength())){
                                Location locationToMove = this.getLocation(xFuturePosition, yFuturePosition);
                                if (animalAmountExceedsLimit(locationToMove, currentAnimal)) {
                                    log.debug("Cannot move: max animals on Location (max = " + maxAnimalsInLocation.get(currentAnimal.getClass()) + "). " + currentAnimal.emoji() + " will stay on the same position: (" + xCurrentPosition + ", " + yCurrentPosition + ").");
                                } else {
                                    locationToMove.addAnimal(currentAnimal);
                                    locationToMove.actualizeAnimalsAmountInLocation(currentAnimal, 1);
                                    currentLocation.removeAnimal(currentAnimal);
                                    currentLocation.actualizeAnimalsAmountInLocation(currentAnimal, -1);
                                    log.debug(currentAnimal.emoji() + " has been moved " + getDirectionByNumber(directionsToMove[l]) + " from position (" + xCurrentPosition + ", " + yCurrentPosition + ") to position (" + xFuturePosition + ", " + yFuturePosition + ") ");
                                    yCurrentPosition = yFuturePosition;
                                    xCurrentPosition = xFuturePosition;
                                    currentLocation = locationToMove;
                                }
                            } else {
                                log.debug("Cannot move: limit of Island. " + currentAnimal.emoji() + " will stay on the same position: (" + xCurrentPosition + ", " + yCurrentPosition + ").");
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


    public void becomeHungryAfterMovement() {
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

    public void reproduceNewAnimal() {
        log.info("reproduceNewAnimal started");
        for (int i = 1; i < this.getWidth() - 1; i++) {
            for (int j = 1; j < this.getLength() - 1; j++) {
                Location currentLocation = this.getLocation(i, j);
                List<Animal> animals = currentLocation.getAnimalsInLocation();
                //--------------------------//
                log.debug("location (" + currentLocation.getPosition().getX() + ","
                        + currentLocation.getPosition().getY() + "): ");
                //--------------------------//
                for (int k = 0; k < animals.size(); k++) {
                    Animal currentAnimal = animals.get(k);
                    if (currentAnimal.fertile() && currentAnimal.getClass().getSimpleName() != "Caterpillar") {
                        //--------------------------//
                        log.debug("current animal: " + currentAnimal);
                        //--------------------------//
                        for (int l = 0; l < animals.size(); l++) {
                            Animal possiblePartner = animals.get(l);
                            if (currentAnimal.meetCriteriaForReproducingChildrenWith(possiblePartner)
                                    && !(this.animalAmountExceedsLimit(currentLocation, currentAnimal))) {
                                Animal babyAnimal;
                                try {
                                    Constructor<? extends Animal> constructor = currentAnimal.getClass().getConstructor();
                                    babyAnimal = constructor.newInstance();
                                    if (babyAnimal instanceof Carnivore) {
                                        PopulationCounter.getInstance().addCarnivore();
                                    } else {
                                        PopulationCounter.getInstance().addHerbivore();
                                    }
                                    babyAnimal.setFertile(false);
                                } catch (NoSuchMethodException | InvocationTargetException | InstantiationException |
                                         IllegalAccessException e) {
                                    throw new RuntimeException(e);
                                }
                                //--------------------------//
                                log.debug("new animal appeared " + babyAnimal + " in location (" + currentLocation.position.getX() + ", " + currentLocation.position.getY() + ").");
                                //--------------------------//
                                currentLocation.addAnimal(babyAnimal);
                                currentLocation.actualizeAnimalsAmountInLocation(babyAnimal, 1);
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

    public void resetFlags() {
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

    public void recoverPlants() {
        log.info("recoverPlants started");
        for (int i = 1; i < this.getWidth() - 1; i++) {
            for (int j = 1; j < this.getLength() - 1; j++) {
                Location currentLocation = this.getLocation(i, j);
                List<Plant> plants = currentLocation.plantsInLocation();
                int oldPlantsAmount = plants.size();
                int amountPlantsToRecover = maxPlantsInLocations - oldPlantsAmount;
                for (int k = 0; k < amountPlantsToRecover; k++) {
                    new Plant();
                }
            }
        }
    }

    public void massCleanUpFromDiedAnimals() {
        log.info("massCleanUpFromDiedAnimals started");
        for (int i = 1; i < this.getWidth() - 1; i++) {
            for (int j = 1; j < this.getLength() - 1; j++) {
                Location currentLocation = this.getLocation(i, j);
                List<Animal> animals = currentLocation.getAnimalsInLocation();
                for (int k = 0; k < animals.size(); k++) {
                    Animal currentAnimal = animals.get(k);
                    if (currentAnimal.currentFullness() <= 0 && !(currentAnimal instanceof Caterpillar)) {
                        currentLocation.diedSpecieCleanUp(currentAnimal);
                        //--------------------------//
                        log.debug(currentAnimal + " died of hunger. Current fullness = " + currentAnimal.currentFullness());
                        //--------------------------//
                    }
                }
            }
        }
    }

    public void feedAnimals() {
        log.info("feedAnimals started");
        Animal animalReadyToEat;
        int probabilityToBeEaten;
        for (int i = 1; i < this.getWidth() - 1; i++) {
            for (int j = 1; j < this.getLength() - 1; j++) {
                Location currentLocation = this.getLocation(i, j);
                List<Animal> animalsReadyToEat = currentLocation.getAnimalsInLocation();
                List<Viable> candidatesToBeEaten;
                //--------------------------//
                log.debug("current location (" + currentLocation.position.getX() + ","
                        + currentLocation.position.getY() + ") before to eat:");
                for (int m = 0; m < animalsReadyToEat.size(); m++) {
                    log.debug("" + animalsReadyToEat.get(m));
                }
                //--------------------------//
                for (int k = animalsReadyToEat.size() - 1; k >= 0; k--) {
                    animalReadyToEat = animalsReadyToEat.get(k);
                    log.debug("animalReadyToEat = " + animalReadyToEat);
                    if (animalReadyToEat.triedToEat() == true) {
                        log.debug(animalReadyToEat + " already ate");
                        continue;
                    }
                    candidatesToBeEaten = currentLocation.generateFoodListFor(animalReadyToEat);
                    for (int l = candidatesToBeEaten.size() - 1; l >= 0; l--) {
                        Viable candidateToBeEaten = candidatesToBeEaten.get(l);
                        if (!animalReadyToEat.canEat(candidateToBeEaten)) {
                            log.debug(animalReadyToEat + " cannot eat " + candidateToBeEaten);
                            continue;
                        }
                        probabilityToBeEaten = foodAndProbabilityRules.getProbabilityByClass(animalReadyToEat, candidateToBeEaten);
                        int randomProbability = generateProbability.getRandomNumber(0, 100);
                        animalReadyToEat.setTriedToEat(true);
                        if (randomProbability <= probabilityToBeEaten) {
                            animalReadyToEat.increaseFullness(candidateToBeEaten);
                            currentLocation.diedSpecieCleanUp(candidateToBeEaten);
                            log.debug(animalReadyToEat + " ate " + candidateToBeEaten + " with probability = " + randomProbability);
                            log.debug("current fullness of " + animalReadyToEat + "=" + animalReadyToEat.currentFullness());
                            break;
                        } else {
                            log.debug(animalReadyToEat + " tried to eat " + candidateToBeEaten + ", but couldn't. Tried to eat = " + animalReadyToEat.triedToEat());
                            break;
                        }
                    }
                }
                //--------------------------//
                log.debug("current location(" + currentLocation.position.getX() + "," + currentLocation.position.getY() + ") after to eat:");
                for (int m = 0; m < animalsReadyToEat.size(); m++) {
                    log.debug("" + animalsReadyToEat.get(m));
                }
                log.debug("Left " + currentLocation.plantsInLocation.size() + " plants");
                //--------------------------//
            }
        }
    }

    public Island buildBorderAroundIsland() {
        int newWidth = gameSettings.getIslandWithBorderWidth();
        int newLength = gameSettings.getIslandWithBorderLength();
        Island islandWithBorder = new Island(newLength, newWidth);
        for (int x = 0; x < newWidth; x++) {
            for (int y = 0; y < newLength; y++) {
                if (locationIsOnBorder(x, y, newWidth, newLength)) {
                    islandWithBorder.locations[x][y] = null;
                } else {
                    islandWithBorder.locations[x][y] = this.getLocation(x - 1, y - 1);
                    islandWithBorder.locations[x][y].setPosition(new Position(x, y));
                }
            }
        }
        return islandWithBorder;
    }

    private boolean locationIsOnBorder(int x, int y, int islandWidth, int islandLength) {
        boolean result = false;
        if (x == 0 || x == islandWidth - 1 || y == 0 || y == islandLength - 1) {
            result = true;
        }
        return result;
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

        public void removePlant(Plant plant) {
            plantsInLocation.remove(plant);
        }

        public List<Plant> plantsInLocation() {
            return plantsInLocation;
        }

        public void addPlant(Plant plant) {
            plantsInLocation.add(plant);
        }

        public void diedSpecieCleanUp(Viable diedSpecie) {
            log.info("diedSpecieCleanUp started");
            if (diedSpecie instanceof Animal) {
                Animal diedAnimal = (Animal) diedSpecie;
                this.removeAnimal(diedAnimal);
                actualizeAnimalsAmountInLocation(diedAnimal, -1);
                //todo может вынести в отд метод? часто использу. эти ifы
                if (Carnivore.class.isAssignableFrom(diedAnimal.getClass())) {
                    PopulationCounter.getInstance().deleteCarnivore();
                } else {
                    PopulationCounter.getInstance().deleteHerbivore();
                }
            } else {
                this.removePlant((Plant) diedSpecie);
                PopulationCounter.getInstance().deletePlant();
            }
            log.debug(diedSpecie + " deleted from location");
        }

        public void actualizeAnimalsAmountInLocation(Animal animal, int coefficient) {
            Class animalClass = animal.getClass();
            int oldAmountAnimalsLocation = this.amountAnimalsByClass(animal.getClass());
            int newAmountAnimalsLocation = oldAmountAnimalsLocation + (coefficient) * 1;
            this.setAmountAnimalsInLocation(animalClass, newAmountAnimalsLocation);
        }

        public void setAmountAnimalsInLocation(Class classAnimal, int amountAnimals) {
            animalsAmountByClass.put(classAnimal, amountAnimals);
        }

        public int amountAnimalsByClass(Class clazz) {
            int animalsAmountByClass = this.animalsAmountByClass.get(clazz);
            return animalsAmountByClass;
        }

        public List<Viable> generateFoodListFor(Animal animal) {
            List<Viable> foodList = new ArrayList<>();
            List<Animal> animalsInLocation = this.getAnimalsInLocation();
            List<Plant> plantsInLocation = this.plantsInLocation();
            if (animal instanceof Herbivore) {
                for (int i = 0; i < animalsInLocation.size(); i++) {
                    if (animalsInLocation.get(i) instanceof Mouse || animalsInLocation.get(i) instanceof Caterpillar) {
                        foodList.add(animalsInLocation.get(i));
                    }
                }
                foodList.addAll(plantsInLocation);
            } else {
                foodList.addAll(animalsInLocation);
            }
            return foodList;
        }

        @Override
        public String toString() {
            return "position=" + position;
        }
    }
}
