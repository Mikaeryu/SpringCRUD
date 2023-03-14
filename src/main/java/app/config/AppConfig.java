package app.config;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class AppConfig {

    @Bean
    public static EntityManagerFactory getEntityManagerFactory() {
        return Persistence.createEntityManagerFactory("hibernate-mvc");
    }
}
