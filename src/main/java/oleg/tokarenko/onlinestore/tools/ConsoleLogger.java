package oleg.tokarenko.onlinestore.tools;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Component
@Primary
public class ConsoleLogger implements Logger {

    @Override
    public void log(String message) {
        DateTimeFormatter dateTimeFormatter =
                DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss");

        System.out.println(LocalDateTime.now().format(dateTimeFormatter) + " : " + message);
    }
}
