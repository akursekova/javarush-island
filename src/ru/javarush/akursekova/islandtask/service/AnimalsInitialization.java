package ru.javarush.akursekova.islandtask.service;
import ru.javarush.akursekova.islandtask.Island;
import ru.javarush.akursekova.islandtask.animals.carnivore.*;
import ru.javarush.akursekova.islandtask.animals.herbivore.*;
import ru.javarush.akursekova.islandtask.animals.plants.Plant;
import ru.javarush.akursekova.islandtask.counter.PopulationCounter;
import ru.javarush.akursekova.islandtask.settings.GameSettings;
public class AnimalsInitialization {
    RandomNumberGenerator randomNumberGenerator = new RandomNumberGenerator();
    GameSettings gameSettings = new GameSettings();

    int islandWidth = gameSettings.getIslandWidth();
    int islandLength = gameSettings.getIslandLength();
    int daysOnTheIsland = gameSettings.getDaysOnTheIsland();

    public void initialize(Island island) {
        System.out.println("Welcome to the 'ISLAND' game" + "\n");
        System.out.println("You will observe island life during " + daysOnTheIsland + " days");
        System.out.println("Your Island has size " + islandLength + " x " + islandWidth);
        System.out.println("Starting fields initialization..." + "\n");
        fillAllAnimals(island);
        System.out.println("Fields are initialized.");
    }

    public void fillAllAnimals(Island island){
        fillCarnivoreAnimals(island);
        System.out.println("Carnivores have been created." + "\n");

        fillHerbivoreAnimals(island);
        System.out.println("Herbivores have been created." + "\n");

        fillPlants(island);
        System.out.println("Plants have been created." + "\n");
    }

    public void fillCarnivoreAnimals(Island island){
        System.out.println("Starting to create carnivores...");
        for (int i = 0; i < island.getWidth(); i++) {
            for (int j = 0; j < island.getLength(); j++) {
                Island.Location currentLocation = island.getLocation(i, j);


//                int maxBearAmountInLocation = island.maxAnimalsInLocation.get(Bear.class);
//                int bearAmountInLocation = randomNumberGenerator.getRandomNumber(0, maxBearAmountInLocation);
//                //int bearAmountInLocation = 1;
//                //System.out.println("boarAmountInLocation" + boarAmountInLocation + "i,j=" + i + ", " + j);
//                for (int k = 0; k < bearAmountInLocation; k++) {
//                    currentLocation.addAnimal(new Bear());
//                    PopulationCounter.getInstance().addCarnivore();
//                }
//                currentLocation.setAmountAnimalsInLocation(Bear.class, bearAmountInLocation);
//
//                int maxBoaAmountInLocation = island.maxAnimalsInLocation.get(Boa.class);
//                int boaAmountInLocation = randomNumberGenerator.getRandomNumber(0, maxBoaAmountInLocation);
//                //int boaAmountInLocation = 1;
//                //System.out.println("boaAmountInLocation" + boaAmountInLocation + "i,j=" + i + ", " + j);
//                for (int k = 0; k < boaAmountInLocation; k++) {
//                    currentLocation.addAnimal(new Boa());
//                    PopulationCounter.getInstance().addCarnivore();
//                }
//                currentLocation.setAmountAnimalsInLocation(Boa.class, boaAmountInLocation);
//
//                int maxEagleAmountInLocation = island.maxAnimalsInLocation.get(Eagle.class);
//                int eagleAmountInLocation = randomNumberGenerator.getRandomNumber(0, maxEagleAmountInLocation);
//                //int eagleAmountInLocation = 1;
//                //System.out.println(eagleAmountInLocation);
//                for (int k = 0; k < eagleAmountInLocation; k++) {
//                    currentLocation.addAnimal(new Eagle());
//                    PopulationCounter.getInstance().addCarnivore();
//                }
//                currentLocation.setAmountAnimalsInLocation(Eagle.class, eagleAmountInLocation);
//
//                int maxFoxAmountInLocation = island.maxAnimalsInLocation.get(Fox.class);
//                int foxAmountInLocation = randomNumberGenerator.getRandomNumber(0, maxFoxAmountInLocation);
//                //int foxAmountInLocation =1;
//                //System.out.println(foxAmountInLocation);
//                for (int k = 0; k < foxAmountInLocation; k++) {
//                    currentLocation.addAnimal(new Fox());
//                    PopulationCounter.getInstance().addCarnivore();
//                }
//                currentLocation.setAmountAnimalsInLocation(Fox.class, foxAmountInLocation);

                int maxWolfAmountInLocation = island.maxAnimalsInLocation.get(Wolf.class);
                //int wolfAmountInLocation = randomNumberGenerator.getRandomNumber(0, maxWolfAmountInLocation);
                int wolfAmountInLocation = 3;
                //System.out.println("wolfAmountInLocation = " + wolfAmountInLocation + "i,j=" + i + ", " + j);
                for (int k = 0; k < wolfAmountInLocation; k++) {
                    currentLocation.addAnimal(new Wolf());
                    PopulationCounter.getInstance().addCarnivore();
                }
                currentLocation.setAmountAnimalsInLocation(Wolf.class, wolfAmountInLocation);
            }
        }
    }

    public void fillHerbivoreAnimals(Island island){
        System.out.println("Starting to create herbivores...");
        for (int i = 0; i < island.getWidth(); i++) {
            for (int j = 0; j < island.getLength(); j++) {

                Island.Location currentLocation = island.getLocation(i, j);

                int maxBoarAmountInLocation = island.maxAnimalsInLocation.get(Boar.class);
                //int boarAmountInLocation = randomNumberGenerator.getRandomNumber(0, maxBoarAmountInLocation);
                int boarAmountInLocation = 3;
                for (int k = 0; k < boarAmountInLocation; k++) {
                    currentLocation.addAnimal(new Boar());
                    PopulationCounter.getInstance().addHerbivore();
                }
                currentLocation.setAmountAnimalsInLocation(Boar.class, boarAmountInLocation);

//                int maxBuffaloAmountInLocation = island.maxAnimalsInLocation.get(Buffalo.class);
//                int buffaloAmountInLocation = randomNumberGenerator.getRandomNumber(0, maxBuffaloAmountInLocation);
//                //int buffaloAmountInLocation =1;
//                for (int k = 0; k < buffaloAmountInLocation; k++) {
//                    currentLocation.addAnimal(new Buffalo());
//                    PopulationCounter.getInstance().addHerbivore();
//                }
//                currentLocation.setAmountAnimalsInLocation(Buffalo.class, buffaloAmountInLocation);

                int maxCaterpillarAmountInLocation = island.maxAnimalsInLocation.get(Caterpillar.class);
                //int caterpillarAmountInLocation = randomNumberGenerator.getRandomNumber(0, maxCaterpillarAmountInLocation);
                int caterpillarAmountInLocation = 3;
                for (int k = 0; k < caterpillarAmountInLocation; k++) {
                    currentLocation.addAnimal(new Caterpillar());
                    PopulationCounter.getInstance().addHerbivore();
                }
                currentLocation.setAmountAnimalsInLocation(Caterpillar.class, caterpillarAmountInLocation);

//                int maxDeerAmountInLocation = island.maxAnimalsInLocation.get(Deer.class);
//                int deerAmountInLocation = randomNumberGenerator.getRandomNumber(0, maxDeerAmountInLocation);
//                //int deerAmountInLocation =1;
//                for (int k = 0; k < deerAmountInLocation; k++) {
//                    currentLocation.addAnimal(new Deer());
//                    PopulationCounter.getInstance().addHerbivore();
//                }
//                currentLocation.setAmountAnimalsInLocation(Deer.class, deerAmountInLocation);

                int maxDuckAmountInLocation = island.maxAnimalsInLocation.get(Duck.class);
                //int duckAmountInLocation = randomNumberGenerator.getRandomNumber(0, maxDuckAmountInLocation);
                int duckAmountInLocation = 3;
                for (int k = 0; k < duckAmountInLocation; k++) {
                    currentLocation.addAnimal(new Duck());
                    PopulationCounter.getInstance().addHerbivore();
                }
                currentLocation.setAmountAnimalsInLocation(Duck.class, duckAmountInLocation);

//                int maxGoatAmountInLocation = island.maxAnimalsInLocation.get(Goat.class);
//                int goatAmountInLocation = randomNumberGenerator.getRandomNumber(0, maxGoatAmountInLocation);
//                int goatAmountInLocation =3;
//                for (int k = 0; k < goatAmountInLocation; k++) {
//                    currentLocation.addAnimal(new Goat());
//                    PopulationCounter.getInstance().addHerbivore();
//                }
//                currentLocation.setAmountAnimalsInLocation(Goat.class, goatAmountInLocation);

//                int horseAmountInLocation = randomNumberGenerator.getRandomNumber(0, island.maxAnimalsInLocation.get(Horse.class));
//                //int horseAmountInLocation = 1;
//                System.out.println("horseAmountInLocation = " + horseAmountInLocation + "i,j=" + i + ", " + j);
//                for (int k = 0; k < horseAmountInLocation; k++) {
//                    currentLocation.addAnimal(new Horse());
//                    PopulationCounter.getInstance().addHerbivore();
//                }
//                currentLocation.setAmountAnimalsInLocation(Horse.class, horseAmountInLocation);
//
//                int maxMouseAmountInLocation = island.maxAnimalsInLocation.get(Mouse.class);
//                int mouseAmountInLocation = randomNumberGenerator.getRandomNumber(0, maxMouseAmountInLocation);
//                //int mouseAmountInLocation =1;
//                for (int k = 0; k < mouseAmountInLocation; k++) {
//                    currentLocation.addAnimal(new Mouse());
//                    PopulationCounter.getInstance().addHerbivore();
//                }
//                currentLocation.setAmountAnimalsInLocation(Mouse.class, mouseAmountInLocation);
//
//                int maxRabbitAmountInLocation = island.maxAnimalsInLocation.get(Rabbit.class);
//                int rabbitAmountInLocation = randomNumberGenerator.getRandomNumber(0, maxRabbitAmountInLocation);
//                //int rabbitAmountInLocation =1;
//                for (int k = 0; k < rabbitAmountInLocation; k++) {
//                    currentLocation.addAnimal(new Rabbit());
//                    PopulationCounter.getInstance().addHerbivore();
//                }
//                currentLocation.setAmountAnimalsInLocation(Rabbit.class, rabbitAmountInLocation);
//
//                int maxSheepAmountInLocation = island.maxAnimalsInLocation.get(Sheep.class);
//                int sheepAmountInLocation = randomNumberGenerator.getRandomNumber(0, maxSheepAmountInLocation);
//                //int sheepAmountInLocation =1;
//                        System.out.println("sheepAmountInLocation = " + sheepAmountInLocation + "i,j=" + i + ", " + j);
//                for (int k = 0; k < sheepAmountInLocation; k++) {
//                    currentLocation.addAnimal(new Sheep());
//                    PopulationCounter.getInstance().addHerbivore();
//                }
//                currentLocation.setAmountAnimalsInLocation(Sheep.class, sheepAmountInLocation);
            }
        }
    }

    public void fillPlants(Island island){
        System.out.println("Starting to create plants...");
        for (int i = 0; i < island.getWidth(); i++) {
            for (int j = 0; j < island.getLength(); j++) {
                Island.Location currentLocation = island.getLocation(i, j);
                for (int k = 0; k < island.maxPlantsInLocations(); k++) {
                    currentLocation.addPlant(new Plant());
                    PopulationCounter.getInstance().addPlant();
                }
            }
        }
    }
}






