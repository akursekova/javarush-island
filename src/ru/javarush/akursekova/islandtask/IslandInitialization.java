package ru.javarush.akursekova.islandtask;
import ru.javarush.akursekova.islandtask.animals.carnivore.*;
import ru.javarush.akursekova.islandtask.animals.herbivore.*;
import ru.javarush.akursekova.islandtask.animals.plants.Plant;
public class IslandInitialization {
    RandomNumberGenerator randomNumberGenerator = new RandomNumberGenerator();

    public void fillCarnivoreAnimals(Island island){
        for (int i = 0; i < island.getWidth(); i++) {
            for (int j = 0; j < island.getLength(); j++) {
                Island.Location currentLocation = island.getLocation(i, j);

//                int bearAmountInLocation = randomNumberGenerator.getRandomNumber(0, island.maxAnimalsInLocation.get(Bear.class));
//                //int bearAmountInLocation = 1;
//                //System.out.println("boarAmountInLocation" + boarAmountInLocation + "i,j=" + i + ", " + j);
//                for (int k = 0; k < bearAmountInLocation; k++) {
//                    currentLocation.addAnimal(new Bear());
//                    PopulationCounter.getInstance().addCarnivore();
//                }
//                currentLocation.setAmountAnimalsInLocation(Bear.class, bearAmountInLocation);
//
//                int boaAmountInLocation = randomNumberGenerator.getRandomNumber(0, island.maxAnimalsInLocation.get(Boa.class));
//                //int boaAmountInLocation = 1;
//                //System.out.println("boaAmountInLocation" + boaAmountInLocation + "i,j=" + i + ", " + j);
//                for (int k = 0; k < boaAmountInLocation; k++) {
//                    currentLocation.addAnimal(new Boa());
//                    PopulationCounter.getInstance().addCarnivore();
//                }
//                currentLocation.setAmountAnimalsInLocation(Boa.class, boaAmountInLocation);
//
//                int eagleAmountInLocation = randomNumberGenerator.getRandomNumber(0, island.maxAnimalsInLocation.get(Eagle.class));
//                //int eagleAmountInLocation = 1;
//                //System.out.println(eagleAmountInLocation);
//                for (int k = 0; k < eagleAmountInLocation; k++) {
//                    currentLocation.addAnimal(new Eagle());
//                    PopulationCounter.getInstance().addCarnivore();
//                }
//                currentLocation.setAmountAnimalsInLocation(Eagle.class, eagleAmountInLocation);
//
//                int foxAmountInLocation = randomNumberGenerator.getRandomNumber(0, island.maxAnimalsInLocation.get(Fox.class));
//                //int foxAmountInLocation =1;
//                //System.out.println(foxAmountInLocation);
//                for (int k = 0; k < foxAmountInLocation; k++) {
//                    currentLocation.addAnimal(new Fox());
//                    PopulationCounter.getInstance().addCarnivore();
//                }
//                currentLocation.setAmountAnimalsInLocation(Fox.class, foxAmountInLocation);

                //int wolfAmountInLocation = randomNumberGenerator.getRandomNumber(0, island.maxAnimalsInLocation.get(Wolf.class));
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
        for (int i = 0; i < island.getWidth(); i++) {
            for (int j = 0; j < island.getLength(); j++) {

                Island.Location currentLocation = island.getLocation(i, j);

                //int boarAmountInLocation = randomNumberGenerator.getRandomNumber(0, island.maxAnimalsInLocation.get(Boar.class));
                int boarAmountInLocation =3;
                for (int k = 0; k < boarAmountInLocation; k++) {
                    currentLocation.addAnimal(new Boar());
                    PopulationCounter.getInstance().addHerbivore();
                }
                currentLocation.setAmountAnimalsInLocation(Boar.class, boarAmountInLocation);

//                int buffaloAmountInLocation = randomNumberGenerator.getRandomNumber(0, island.maxAnimalsInLocation.get(Buffalo.class));
//                //int buffaloAmountInLocation =1;
//                for (int k = 0; k < buffaloAmountInLocation; k++) {
//                    currentLocation.addAnimal(new Buffalo());
//                    PopulationCounter.getInstance().addHerbivore();
//                }
//                currentLocation.setAmountAnimalsInLocation(Buffalo.class, buffaloAmountInLocation);

                //int caterpillarAmountInLocation = randomNumberGenerator.getRandomNumber(0, island.maxAnimalsInLocation.get(Caterpillar.class));
//                int caterpillarAmountInLocation = 3;
//                for (int k = 0; k < caterpillarAmountInLocation; k++) {
//                    currentLocation.addAnimal(new Caterpillar());
//                    PopulationCounter.getInstance().addHerbivore();
//                }
//                currentLocation.setAmountAnimalsInLocation(Caterpillar.class, caterpillarAmountInLocation);

//                int deerAmountInLocation = randomNumberGenerator.getRandomNumber(0, island.maxAnimalsInLocation.get(Deer.class));
//                //int deerAmountInLocation =1;
//                for (int k = 0; k < deerAmountInLocation; k++) {
//                    currentLocation.addAnimal(new Deer());
//                    PopulationCounter.getInstance().addHerbivore();
//                }
//                currentLocation.setAmountAnimalsInLocation(Deer.class, deerAmountInLocation);

                //int duckAmountInLocation = randomNumberGenerator.getRandomNumber(0, island.maxAnimalsInLocation.get(Duck.class));
                int duckAmountInLocation =3;
                for (int k = 0; k < duckAmountInLocation; k++) {
                    currentLocation.addAnimal(new Duck());
                    PopulationCounter.getInstance().addHerbivore();
                }
                currentLocation.setAmountAnimalsInLocation(Duck.class, duckAmountInLocation);

                //int goatAmountInLocation = randomNumberGenerator.getRandomNumber(0, island.maxAnimalsInLocation.get(Goat.class));
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

                //int mouseAmountInLocation = randomNumberGenerator.getRandomNumber(0, island.maxAnimalsInLocation.get(Mouse.class));
//                int mouseAmountInLocation =1;
//                for (int k = 0; k < mouseAmountInLocation; k++) {
//                    currentLocation.addAnimal(new Mouse());
//                    PopulationCounter.getInstance().addHerbivore();
//                }
//                currentLocation.setAmountAnimalsInLocation(Mouse.class, mouseAmountInLocation);

//                int rabbitAmountInLocation = randomNumberGenerator.getRandomNumber(0, island.maxAnimalsInLocation.get(Rabbit.class));
//                //int rabbitAmountInLocation =1;
//                for (int k = 0; k < rabbitAmountInLocation; k++) {
//                    currentLocation.addAnimal(new Rabbit());
//                    PopulationCounter.getInstance().addHerbivore();
//                }
//                currentLocation.setAmountAnimalsInLocation(Rabbit.class, rabbitAmountInLocation);
//
//                int sheepAmountInLocation = randomNumberGenerator.getRandomNumber(0, island.maxAnimalsInLocation.get(Sheep.class));
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
        for (int i = 0; i < island.getWidth(); i++) {
            for (int j = 0; j < island.getLength(); j++) {
                Island.Location currentLocation = island.getLocation(i, j);
                for (int k = 0; k < island.maxPlantsInLocations(); k++) {
                    currentLocation.addPlant(new Plant());
                }
            }
        }

    }
}






