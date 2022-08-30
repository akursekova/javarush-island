package ru.javarush.akursekova.islandtask.logging;

import ru.javarush.akursekova.islandtask.exception.LoggerInitializationException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    private static final String SEPARATOR = System.getProperty("file.separator");
    private static final String OUTPUT_DIRECTORY = "logs";
    private static final String OUTPUT_FILENAME = "output.log";
    private static final String OUTPUT_FILEPATH = OUTPUT_DIRECTORY + SEPARATOR + OUTPUT_FILENAME;
    private static final String ERROR_LOGGER_INIT = "Error during logger initialization";

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    private static Logger INSTANCE;

    private Path outputFilePath;

    private Logger() throws IOException {
        validateAndCreateDirectory();
        validateAndCreateLogFile();

        outputFilePath = Path.of(OUTPUT_FILEPATH);
    }

    public static Logger getInstance() throws LoggerInitializationException {
        if (INSTANCE == null) {
            try {
                INSTANCE = new Logger();
            } catch (IOException ex) {
                throw new LoggerInitializationException(ERROR_LOGGER_INIT);
            }
        }
        return INSTANCE;
    }

    public void info(String message) {
        writeLog("INFO: " + message);
    }

    public void debug(String message) {
        writeLog("DEBUG: " + message);
    }

    public void error(String message) {
        writeLog("ERROR: " + message);
    }

    private void validateAndCreateDirectory() throws IOException {
        Path outputDirectory = Path.of(OUTPUT_DIRECTORY);
        if (!Files.exists(outputDirectory)) {
            Files.createDirectory(outputDirectory);
        }
    }

    private void validateAndCreateLogFile() throws IOException {
        Path outputFile = Path.of(OUTPUT_FILENAME);
        if (!Files.exists(outputFile)) {
            Files.createFile(outputFile);
        }
    }


    private void writeLog(String message) {
        try {
            String timestamp = "[" + (LocalDateTime.now().format(DATE_TIME_FORMATTER)) + "]";
            Files.writeString(outputFilePath, timestamp + message + "\n", StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.err.println("Logger doesn't work properly. Error message: " + e.getMessage());
        }
    }
}
