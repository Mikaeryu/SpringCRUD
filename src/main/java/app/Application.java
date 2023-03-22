package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.apache.logging.slf4j.SLF4JProvider;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        //PropertyConfigurator.configure("d:\\Java\\JavaRushTasks\\4.JavaCollections\\log4j.properties");
        SpringApplication.run(Application.class, args);
    }
}
