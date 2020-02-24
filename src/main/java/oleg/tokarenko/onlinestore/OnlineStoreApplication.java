package oleg.tokarenko.onlinestore;

import oleg.tokarenko.onlinestore.tools.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class OnlineStoreApplication {

    @Autowired
    private Logger logger;

    @PostConstruct
    public void init() {
        logger.log("OnlineStoreApplication.init(): start");
        logger.log("OnlineStoreApplication.init(): end");
    }

    public static void main(String[] args) {
        SpringApplication.run(OnlineStoreApplication.class, args);
    }
}
