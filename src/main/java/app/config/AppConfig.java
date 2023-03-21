package app.config;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Component
public class AppConfig {
    private static EntityManagerFactory getEntityManagerFactory() {
        return Persistence.createEntityManagerFactory("hibernate-config");
    }

    @Bean
    public static EntityManager getEntityManager() {
        return getEntityManagerFactory().createEntityManager();
    }
}
