package app.config;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Component
public class AppConfig {
    @Bean
    public static EntityManagerFactory getEntityManagerFactory() {
        return Persistence.createEntityManagerFactory("hibernate-config");
    }
}
