package app.config;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(value = "app")
public class AppConfig {

    @Bean
    public static EntityManagerFactory getEntityManagerFactory() {
        return Persistence.createEntityManagerFactory("CRM");
    }
}
