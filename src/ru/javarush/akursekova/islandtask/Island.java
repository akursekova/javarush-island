package ru.javarush.akursekova.islandtask;

import ru.javarush.akursekova.islandtask.animals.Viable;
import ru.javarush.akursekova.islandtask.animals.abstracts.Animal;
import ru.javarush.akursekova.islandtask.animals.abstracts.Carnivore;
import ru.javarush.akursekova.islandtask.animals.abstracts.Herbivore;
import ru.javarush.akursekova.islandtask.animals.carnivore.*;
import ru.javarush.akursekova.islandtask.animals.herbivore.*;
import ru.javarush.akursekova.islandtask.animals.plants.Plant;
import ru.javarush.akursekova.islandtask.counter.PopulationCounter;
import ru.javarush.akursekova.islandtask.exception.AnimalCreationException;
import ru.javarush.akursekova.islandtask.logging.Logger;
import ru.javarush.akursekova.islandtask.service.RandomNumberGenerator;
import ru.javarush.akursekova.islandtask.settings.FoodAndProbabilityRules;
import ru.javarush.akursekova.islandtask.settings.GameSettings;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class Island {
    private static final String ERROR_ANIMAL_INIT = "Error during Animal initialization with class %s";

    private static final String LOG_ANIMAL_CANNOT_MOVE_LOC_LIMIT = "Cannot move: max animals on Location (max = %d). %s will stay on the same position: (%d, %d).";
    private static final String LOG_ANIMAL_CANNOT_MOVE_ISLAND_LIMIT = "Cannot move: limit of Island. %s will stay on the same position: (%d, %d).";
    private static final String LOG_ISLAND_CREATED = "Island with length = %d and width = %d successfully created.";
    private static final String LOG_ANIMAL_MOVED = "%s has been moved %d from position (%d, %d) to position (%d, %d).";
    private static final String LOG_ANIMAL_CURRENT_FULLNESS = "%s: current fullness = %,.3f";
    private static final String LOG_ANIMAL_REPRODUCED_ANIMAL = "%s with %s reproduced new animal %s in location %s.";
    private static final String LOG_RECOVERY = "plants population %s recovery = %d";
    private static final String LOG_ANIMAL_DIED = "%s died of hunger. Current fullness = %,.3f";
    private static final String LOG_LOC_STATE_EAT = "current location %s %s to eat:";
    private static final String LOG_ANIMAL_ATE_ANIMAL = "%s ate %s with probability = %d";
    private static final String LOG_ANIMAL_TRIED_EAT = "%s tried to eat %s, but couldn't. Tried to eat = %b";

    private static final String LOG_METHOD_STARTED = "%s method started";
    private static final String LOG_METHOD_FINISHED = "%s method finished";
    private static final String LOG_CURRENT_LOCATION = "currentLocation = %s";
    private static final String LOG_CURRENT_ANIMAL = "current animal = %s";

    private static final String LOG_ANIMAL_READY_TO_EAT = "animalReadyToEat = %s";
    private static final String LOG_ANIMAL_ALREADY_ATE = "%s already ate";
    private static final String LOG_ANIMAL_CANNOT_EAT = "%s cannot eat %s";
    private static final String LOG_ALREADY_MOVED = "already moved";
    private static final String LOG_ANIMAL_DELETED = "%s deleted from location";

    private static final String LOG_LEFT_PLANTS = "Left %d plants.";

    private static final String LOG_FEED_ANIMALS = "feedAnimals";
    private static final String LOG_MOVE_ANIMALS = "moveAnimals";
    private static final String LOG_RESET_FLAGS = "resetFlags";
    private static final String LOG_RECOVER_PLANTS = "recoverPlants";
    private static final String LOG_REPRODUCE_ANIMAL = "reproduceNewAnimal";
    private static final String LOG_BECOME_HUNGRY = "becomeHungryAfterMovement";
    private static final String LOG_DIED_SPECIE_CLEANUP = "diedSpecieCleanUp";
    private static final String LOG_MASS_CLEANUP = "massCleanUpFromDiedAnimals";

    private static final String BEFORE = "before";
    private static final String AFTER = "after";
    private static final String LOG_EMPTY_STRING = "%s";

    public static final Logger log = Logger.getInstance();

    GameSettings gameSettings = new GameSettings();
    FoodAndProbabilityRules foodAndProbabilityRules = new FoodAndProbabilityRules();
    RandomNumberGenerator generateProbability = new RandomNumberGenerator();

    private int length;
    private int width;
    private Location[][] locations;
    private int maxPlantsInLocations = 200;

    public Map<Class, Integer> maxAnimalsInLocation = new HashMap<>() {{
        put(Bear.class, 5);
        put(Boa.class, 30);
        put(Eagle.class, 20);
        put(Fox.class, 30);
        put(Wolf.class, 30);
        put(Boar.class, 50);
        put(Buffalo.class, 10);
        put(Caterpillar.class, 1000);
        put(Deer.class, 20);
        put(Duck.class, 200);
        put(Goat.class, 140);
        put(Horse.class, 20);
        put(Mouse.class, 500);
        put(Rabbit.class, 150);
        put(Sheep.class, 140);
    }};

    public Island(int length, int width) {
        locations = new Location[width][length];
        this.length = length;
        this.width = width;
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < length; y++) {
                locations[x][y] = new Location(new Position(x, y));
            }
        }
        log.debug(String.format(LOG_ISLAND_CREATED, length, width));
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

    public int calculatePosition(int position, int coefficient) {
        return position + coefficient;
    }

    public Position getPositionLocationToMove(int direction, int xCurrentPosition, int yCurrentPosition) {
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
        log.info(String.format(LOG_METHOD_STARTED, LOG_MOVE_ANIMALS));
        for (int i = 1; i < this.getWidth() - 1; i++) {
            for (int j = 1; j < this.getLength() - 1; j++) {
                Location currentLocation = this.getLocation(i, j);
                log.debug(String.format(LOG_CURRENT_LOCATION, currentLocation));
                List<Animal> animals = currentLocation.getAnimalsInLocation();
                for (int k = animals.size() - 1; k >= 0; k--) {
                    Animal currentAnimal = animals.get(k);
                    int xCurrentPosition = i;
                    int yCurrentPosition = j;
                    int xFuturePosition;
                    int yFuturePosition;
                    int[] directionsToMove = currentAnimal.generateDirectionsToMove();
                    log.debug(String.format(LOG_CURRENT_ANIMAL, currentAnimal));
                    for (int l = 0; l < directionsToMove.length; l++) {
                        log.debug(directionsToMove[l] + " ");
                    }
                    for (int l = 0; l < directionsToMove.length; l++) {
                        if (currentAnimal.moved()) {
                            log.debug(String.format(LOG_CURRENT_ANIMAL, LOG_ALREADY_MOVED));
                            continue;
                        } else {
                            Position futurePosition = getPositionLocationToMove(directionsToMove[l], xCurrentPosition, yCurrentPosition);
                            xFuturePosition = futurePosition.getX();
                            yFuturePosition = futurePosition.getY();
                            if (!locationIsOnBorder(xFuturePosition, yFuturePosition, gameSettings.getIslandWithBorderWidth(), gameSettings.getIslandWithBorderLength())) {
                                Location locationToMove = this.getLocation(xFuturePosition, yFuturePosition);
                                if (animalAmountExceedsLimit(locationToMove, currentAnimal)) {
                                    log.debug(String.format(LOG_ANIMAL_CANNOT_MOVE_LOC_LIMIT,
                                            maxAnimalsInLocation.get(currentAnimal.getClass()),
                                            currentAnimal.emoji(), xCurrentPosition, yCurrentPosition));
                                } else {
                                    locationToMove.addAnimal(currentAnimal);
                                    locationToMove.actualizeAnimalsAmountInLocation(currentAnimal, 1);
                                    currentLocation.removeAnimal(currentAnimal);
                                    currentLocation.actualizeAnimalsAmountInLocation(currentAnimal, -1);
                                    log.debug(String.format(LOG_ANIMAL_MOVED, currentAnimal.emoji(), directionsToMove[l],
                                            xCurrentPosition, yCurrentPosition, xFuturePosition, yFuturePosition));
                                    yCurrentPosition = yFuturePosition;
                                    xCurrentPosition = xFuturePosition;
                                    currentLocation = locationToMove;
                                }
                            } else {
                                log.debug(String.format(LOG_ANIMAL_CANNOT_MOVE_ISLAND_LIMIT, currentAnimal,
                                        xCurrentPosition, yCurrentPosition));
                            }
                        }
                    }
                    currentAnimal.setMoved(true);
                    currentLocation = this.getLocation(i, j);
                }
            }
        }
    }

    public void becomeHungryAfterMovement() {
        log.info(String.format(LOG_METHOD_STARTED, LOG_BECOME_HUNGRY));
        for (int i = 1; i < this.getWidth() - 1; i++) {
            for (int j = 1; j < this.getLength() - 1; j++) {
                Location currentLocation = this.getLocation(i, j);
                List<Animal> animals = currentLocation.getAnimalsInLocation();
                for (int k = 0; k < animals.size(); k++) {
                    Animal currentAnimal = animals.get(k);
                    currentAnimal.reduceFullness();
                    log.debug(String.format(LOG_ANIMAL_CURRENT_FULLNESS, currentAnimal, currentAnimal.currentFullness()));
                }
            }
        }
        log.info(String.format(LOG_METHOD_FINISHED, LOG_BECOME_HUNGRY));
    }

    public void reproduceNewAnimal() throws AnimalCreationException {
        log.info(String.format(LOG_METHOD_STARTED, LOG_REPRODUCE_ANIMAL));
        for (int i = 1; i < this.getWidth() - 1; i++) {
            for (int j = 1; j < this.getLength() - 1; j++) {
                Location currentLocation = this.getLocation(i, j);
                List<Animal> animals = currentLocation.getAnimalsInLocation();
                log.debug(String.format(LOG_CURRENT_LOCATION, currentLocation));
                for (int k = 0; k < animals.size(); k++) {
                    Animal currentAnimal = animals.get(k);
                    if (currentAnimal.fertile() && !(currentAnimal instanceof Caterpillar)) {
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
                                    throw new AnimalCreationException(String.format(ERROR_ANIMAL_INIT, currentAnimal.getClass()));
                                }
                                log.debug(String.format(LOG_ANIMAL_REPRODUCED_ANIMAL, currentAnimal,
                                        possiblePartner, babyAnimal, currentLocation));
                                currentLocation.addAnimal(babyAnimal);
                                currentLocation.actualizeAnimalsAmountInLocation(babyAnimal, 1);
                                currentAnimal.setFertile(false);
                                possiblePartner.setFertile(false);
                                break;
                            }
                        }
                    }
                }
            }
        }
    }

    public void resetFlags() {
        log.info(String.format(LOG_METHOD_STARTED, LOG_RESET_FLAGS));
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
        log.info(String.format(LOG_METHOD_STARTED, LOG_RECOVER_PLANTS));
        log.debug(String.format(LOG_RECOVERY, BEFORE, PopulationCounter.getInstance().getPlants()));
        for (int i = 1; i < this.getWidth() - 1; i++) {
            for (int j = 1; j < this.getLength() - 1; j++) {
                Location currentLocation = this.getLocation(i, j);
                List<Plant> plants = currentLocation.plantsInLocation();
                int oldPlantsAmount = plants.size();
                int amountPlantsToRecover = maxPlantsInLocations - oldPlantsAmount;
                for (int k = 0; k < amountPlantsToRecover; k++) {
                    currentLocation.addPlant(new Plant());
                    PopulationCounter.getInstance().addPlant();
                }
            }
        }
        log.debug(String.format(LOG_RECOVERY, AFTER, PopulationCounter.getInstance().getPlants()));
    }

    public void massCleanUpFromDiedAnimals() {
        log.info(String.format(LOG_METHOD_STARTED, LOG_MASS_CLEANUP));
        for (int i = 1; i < this.getWidth() - 1; i++) {
            for (int j = 1; j < this.getLength() - 1; j++) {
                Location currentLocation = this.getLocation(i, j);
                List<Animal> animals = currentLocation.getAnimalsInLocation();
                for (int k = 0; k < animals.size(); k++) {
                    Animal currentAnimal = animals.get(k);
                    if (currentAnimal.currentFullness() <= 0 && !(currentAnimal instanceof Caterpillar)) {
                        currentLocation.diedSpecieCleanUp(currentAnimal);
                        log.debug(String.format(LOG_ANIMAL_DIED, currentAnimal, currentAnimal.currentFullness()));
                    }
                }
            }
        }
    }

    public void feedAnimals() {
        log.info(String.format(LOG_METHOD_STARTED, LOG_FEED_ANIMALS));
        Animal animalReadyToEat;
        int probabilityToBeEaten;
        for (int i = 1; i < this.getWidth() - 1; i++) {
            for (int j = 1; j < this.getLength() - 1; j++) {
                Location currentLocation = this.getLocation(i, j);
                List<Animal> animalsReadyToEat = currentLocation.getAnimalsInLocation();
                List<Viable> candidatesToBeEaten;

                log.debug(String.format(LOG_LOC_STATE_EAT, currentLocation, BEFORE));
                for (int m = 0; m < animalsReadyToEat.size(); m++) {
                    log.debug(String.format(LOG_EMPTY_STRING, animalsReadyToEat.get(m)));
                }

                for (int k = animalsReadyToEat.size() - 1; k >= 0; k--) {
                    animalReadyToEat = animalsReadyToEat.get(k);
                    log.debug(String.format(LOG_ANIMAL_READY_TO_EAT, animalReadyToEat));
                    if (animalReadyToEat.triedToEat()) {
                        log.debug(String.format(LOG_ANIMAL_ALREADY_ATE, animalReadyToEat));
                        continue;
                    }
                    candidatesToBeEaten = currentLocation.generateFoodListFor(animalReadyToEat);
                    for (int l = candidatesToBeEaten.size() - 1; l >= 0; l--) {
                        Viable candidateToBeEaten = candidatesToBeEaten.get(l);
                        if (!animalReadyToEat.canEat(candidateToBeEaten)) {
                            log.debug(String.format(LOG_ANIMAL_CANNOT_EAT, animalReadyToEat, candidateToBeEaten));
                            continue;
                        }
                        probabilityToBeEaten = foodAndProbabilityRules.getProbabilityByClass(animalReadyToEat, candidateToBeEaten);
                        int randomProbability = generateProbability.getRandomNumber(0, 100);
                        animalReadyToEat.setTriedToEat(true);
                        if (randomProbability <= probabilityToBeEaten) {
                            animalReadyToEat.increaseFullness(candidateToBeEaten);
                            currentLocation.diedSpecieCleanUp(candidateToBeEaten);
                            log.debug(String.format(LOG_ANIMAL_ATE_ANIMAL, animalReadyToEat,
                                    candidateToBeEaten, randomProbability));
                            log.debug(String.format(LOG_ANIMAL_CURRENT_FULLNESS, animalReadyToEat,
                                    animalReadyToEat.currentFullness()));
                            break;
                        } else {

                            log.debug(String.format(LOG_ANIMAL_TRIED_EAT, animalReadyToEat, candidateToBeEaten,
                                    animalReadyToEat.triedToEat()));
                            break;
                        }
                    }
                }

                log.debug(String.format(LOG_LOC_STATE_EAT, currentLocation, AFTER));
                for (int m = 0; m < animalsReadyToEat.size(); m++) {
                    log.debug(String.format(LOG_EMPTY_STRING, animalsReadyToEat.get(m)));
                }
                log.debug(String.format(LOG_LEFT_PLANTS, currentLocation.plantsInLocation.size()));
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
            log.info(String.format(LOG_METHOD_STARTED, LOG_DIED_SPECIE_CLEANUP));
            if (diedSpecie instanceof Animal) {
                Animal diedAnimal = (Animal) diedSpecie;
                this.removeAnimal(diedAnimal);
                actualizeAnimalsAmountInLocation(diedAnimal, -1);
                if (Carnivore.class.isAssignableFrom(diedAnimal.getClass())) {
                    PopulationCounter.getInstance().deleteCarnivore();
                } else {
                    PopulationCounter.getInstance().deleteHerbivore();
                }
            } else {
                this.removePlant((Plant) diedSpecie);
                PopulationCounter.getInstance().deletePlant();
            }

            log.debug(String.format(LOG_ANIMAL_DELETED, diedSpecie));
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
            return this.animalsAmountByClass.get(clazz);
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
