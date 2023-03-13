package app.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class AppConfig {

    @Bean
    public static EntityManagerFactory getEntityManagerFactory() {
        return Persistence.createEntityManagerFactory("hibernate-mvc");
    }

    @Bean
    @Scope("prototype")
    public static EntityManager getEntityManager() {
        return getEntityManagerFactory().createEntityManager();
    }
}
