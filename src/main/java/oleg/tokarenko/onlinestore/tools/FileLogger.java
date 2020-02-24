package oleg.tokarenko.onlinestore.tools;

import org.springframework.stereotype.Component;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class FileLogger implements Logger {

    @Override
    public void log(String message) {
        try (FileWriter fileWriter = new FileWriter("log.txt", true);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {

            DateTimeFormatter dateTimeFormatter =
                    DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss");

            bufferedWriter.write(LocalDateTime.now().format(dateTimeFormatter) + " : " + message + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
