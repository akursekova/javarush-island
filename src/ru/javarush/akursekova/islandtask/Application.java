package ru.javarush.akursekova.islandtask;

import ru.javarush.akursekova.islandtask.service.ProcessManager;

public class Application {

    public static void main(String[] args) {
        ProcessManager processManager = new ProcessManager();
        processManager.run();
    }
}
