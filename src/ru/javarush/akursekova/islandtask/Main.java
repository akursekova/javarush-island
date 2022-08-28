package ru.javarush.akursekova.islandtask;

import ru.javarush.akursekova.islandtask.animals.abstracts.Animal;
import ru.javarush.akursekova.islandtask.animals.carnivore.Wolf;
import ru.javarush.akursekova.islandtask.service.ProcessManager;

public class Main {

    public static void main(String[] args) {
        ProcessManager processManager = new ProcessManager();
        processManager.run();
    }
}
