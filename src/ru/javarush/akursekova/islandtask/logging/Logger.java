package ru.javarush.akursekova.islandtask.logging;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Logger {
    // to get separator in a path, specific for OS
    private static final String SEPARATOR = System.getProperty("file.separator");
    private static final String OUTPUT_DIRECTORY = "logs";
    private static final String OUTPUT_FILENAME = "output.log";
    private static final String OUTPUT_FILEPATH = OUTPUT_DIRECTORY+SEPARATOR+OUTPUT_FILENAME;

    //  задаем как будет записываться время в нашем логе
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    private static Logger INSTANCE;

    private Path outputFilePath;

    private Logger() throws IOException {
        validateAndCreateDirectory(); // проверяем есть ли такая папка. Если нет, создаем.
        validateAndCreateLogFile(); // проверяем есть ли такой файл. Если нет, создаем.

        outputFilePath = Path.of(OUTPUT_FILEPATH);
    }

    // метод который позволяет сделать Логгер синглтоном. У всез будет один экземпляр этого класа.
    public static Logger getInstance(){
        if (INSTANCE == null){
            try {
                INSTANCE = new Logger();
            } catch (IOException ex){
                // возобновить в памяти что пишем в собственном exception
                //throw new LoggerInitializationException("Error during logger initialization");
            }
        }
        return INSTANCE;
    }

    // разные уровни записи. Для создания уровней логов. Но логи пишутся в один файл.
    // info - средней важности
    public void info(String message){
        writeLog("INFO: " + message);
    }

    // этого не продакшене нет
    // отладочная инфа, нужна, когда нет debugger
    public void debug(String message){
        writeLog("DEBUG: " + message);
    }

    // всегда должен быть на продакшене
    public void error(String message){
        writeLog("ERROR: " + message);
    }

    private void validateAndCreateDirectory() throws IOException {
        Path outputDirectory = Path.of(OUTPUT_DIRECTORY);
        if (!Files.exists(outputDirectory)){
            Files.createDirectory(outputDirectory);
        }
    }

    private void validateAndCreateLogFile() throws IOException {
        Path outputFile = Path.of(OUTPUT_FILENAME);
        if (!Files.exists(outputFile)){
            Files.createFile(outputFile);
        }
    }

    // message - сообщение, которое нам поступило залогировать
    private void writeLog(String message){
        try {
            // DATE_TIME_FORMATTER - формат даты
            String timestamp = "[" + (LocalDateTime.now().format(DATE_TIME_FORMATTER)) + "]";
            // пишем сообщение с текущей датой в наш файл
            Files.writeString(outputFilePath, timestamp + message + "\n", StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.err.println("Logger doesn't work properly. Error message: " + e.getMessage());
        }
    }
}
