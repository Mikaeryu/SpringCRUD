package app.config;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import javax.transaction.Transactional;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AppConfig {

//    @PersistenceUnit(unitName = "hibernate-config")
//    private EntityManagerFactory entityManagerFactory;

    private static EntityManagerFactory getEntityManagerFactory() {
        return Persistence.createEntityManagerFactory("hibernate-config");
    }

    @Bean
    public EntityManager getEntityManager() {
        return getEntityManagerFactory().createEntityManager();
    }
}
